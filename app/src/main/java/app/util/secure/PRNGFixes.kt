package app.util.secure

import android.os.Build
import android.os.Process
import app.util.ext.dataInputStream
import app.util.ext.dataOutputStream

import java.io.ByteArrayOutputStream
import java.io.DataInputStream
import java.io.File
import java.io.IOException
import java.io.UnsupportedEncodingException
import java.security.NoSuchAlgorithmException
import java.security.Provider
import java.security.SecureRandom
import java.security.SecureRandomSpi
import java.security.Security

/**
 * Fixes for the output of the default PRNG having low entropy.
 *
 * The fixes need to be applied via [.apply] before any use of Java
 * Cryptography Architecture primitives. A good place to invoke them is in the
 * application's `onCreate`.
 *
 * Note: SHA1PRNG is cryptographically weak and was deprecated in Android N:
 * https://developer.android.com/preview/behavior-changes.html#other
 *
 * @see http://android-developers.blogspot.com/2013/08/some-securerandom-thoughts.html
 */
object PRNGFixes {
    private const val VERSION_CODE_JELLY_BEAN = 16
    private const val VERSION_CODE_JELLY_BEAN_MR2 = 18

    private const val DEV_URANDOM = "/dev/urandom"

    /**
     * Gets the hardware serial number of this device.
     *
     * @return serial number or `null` if not available.
     */

    private val deviceSerialNumber: String? by lazy {
        // We're using the Reflection API because Build.SERIAL is only available
        // since API Level 9 (Gingerbread, Android 2.3).
        try {
            Build::class.java.getField("SERIAL").get(null) as String?
        } catch (ignored: Exception) {
            null
        }
    }

    private val buildFingerprintAndDeviceSerial: ByteArray by lazy {
        try {
            buildString {
                append(Build.FINGERPRINT)
                append(deviceSerialNumber)
            }.toByteArray(Charsets.UTF_8)
        } catch (e: UnsupportedEncodingException) {
            throw RuntimeException("UTF-8 encoding not supported")
        }
    }

    /**
     * Applies all fixes.
     *
     * @throws SecurityException if a fix is needed but could not be applied.
     */
    fun apply() {
        if (Build.VERSION.SDK_INT in VERSION_CODE_JELLY_BEAN..VERSION_CODE_JELLY_BEAN_MR2) {
            applyOpenSSLFix()
        }
        if (Build.VERSION.SDK_INT <= VERSION_CODE_JELLY_BEAN_MR2) {
            installLinuxPRNGSecureRandom()
        }
    }

    /**
     * Applies the fix for OpenSSL PRNG having low entropy. Does nothing if the
     * fix is not needed.
     *
     * @throws SecurityException if the fix is needed but could not be applied.
     */
    @Throws(SecurityException::class)
    private fun applyOpenSSLFix() {
        if ("robolectric" == Build.FINGERPRINT) {
            // if we are in robolectric we avoid this fix: the referenced classes don't exist
            return
        }

        try {
            // Mix in the device- and invocation-specific seed.
            Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto")
                    .getMethod("RAND_seed", ByteArray::class.java)
                    .invoke(null, generateSeed())

            // Mix output of Linux PRNG into OpenSSL's PRNG
            val bytesRead = Class.forName(
                    "org.apache.harmony.xnet.provider.jsse.NativeCrypto")
                    .getMethod("RAND_load_file", String::class.java, Long::class.javaPrimitiveType)
                    .invoke(null, DEV_URANDOM, 1024) as Int
            if (bytesRead != 1024) {
                throw IOException("Unexpected number of bytes read from Linux PRNG: $bytesRead")
            }
        } catch (e: Exception) {
            throw SecurityException("Failed to seed OpenSSL PRNG", e)
        }

    }

    /**
     * Installs a Linux PRNG-backed `SecureRandom` implementation as the
     * default. Does nothing if the implementation is already the default or if
     * there is not need to install the implementation.
     *
     * @throws SecurityException if the fix is needed but could not be applied.
     */
    @Throws(SecurityException::class)
    private fun installLinuxPRNGSecureRandom() {
        // Install a Linux PRNG-based SecureRandom implementation as the
        // default, if not yet installed.
        val secureRandomProviders = Security.getProviders("SecureRandom.SHA1PRNG")
        if (secureRandomProviders == null
                || secureRandomProviders.isEmpty()
                || LinuxPRNGSecureRandomProvider::class.java != secureRandomProviders[0].javaClass) {
            Security.insertProviderAt(LinuxPRNGSecureRandomProvider(), 1)
        }

        // Assert that new SecureRandom() and
        // SecureRandom.getInstance("SHA1PRNG") return a SecureRandom backed
        // by the Linux PRNG-based SecureRandom implementation.
        val rng1 = SecureRandom()
        if (LinuxPRNGSecureRandomProvider::class.java != rng1.provider.javaClass) {
            throw SecurityException("new SecureRandom() backed by wrong Provider: ${rng1.provider.javaClass}")
        }

        val rng2 = try {
            SecureRandom.getInstance("SHA1PRNG")
        } catch (e: NoSuchAlgorithmException) {
            throw SecurityException("SHA1PRNG not available", e)
        }

        if (LinuxPRNGSecureRandomProvider::class.java != rng2.provider.javaClass) {
            throw SecurityException("SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: ${rng2.provider.javaClass}")
        }
    }

    /**
     * Generates a device- and invocation-specific seed to be mixed into the
     * Linux PRNG.
     */
    private fun generateSeed(): ByteArray {
        try {
            val seedBuffer = ByteArrayOutputStream()
            seedBuffer.dataOutputStream().use {
                it.writeLong(System.currentTimeMillis())
                it.writeLong(System.nanoTime())
                it.writeInt(Process.myPid())
                it.writeInt(Process.myUid())
                it.write(buildFingerprintAndDeviceSerial)
            }
            return seedBuffer.toByteArray()
        } catch (e: IOException) {
            throw SecurityException("Failed to generate seed", e)
        }
    }

    /**
     * `Provider` of `SecureRandom` engines which pass through
     * all requests to the Linux PRNG.
     */
    private class LinuxPRNGSecureRandomProvider : Provider(
            "LinuxPRNG",
            1.0,
            "A Linux-specific random number provider that uses $DEV_URANDOM") {
        init {
            // Although /dev/urandom is not a SHA-1 PRNG, some apps
            // explicitly request a SHA1PRNG SecureRandom and we thus need to
            // prevent them from getting the default implementation whose output
            // may have low entropy.
            put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom::class.java.name)
            put("SecureRandom.SHA1PRNG ImplementedIn", "Software")
        }
    }

    /**
     * [SecureRandomSpi] which passes all requests to the Linux PRNG
     * (`/dev/urandom`).
     */
    class LinuxPRNGSecureRandom : SecureRandomSpi() {
        /*
         * IMPLEMENTATION NOTE: Requests to generate bytes and to mix in a seed
         * are passed through to the Linux PRNG (/dev/urandom). Instances of
         * this class seed themselves by mixing in the current time, PID, UID,
         * build fingerprint, and hardware serial number (where available) into
         * Linux PRNG.
         *
         * Concurrency: Read requests to the underlying Linux PRNG are
         * serialized (on sLock) to ensure that multiple threads do not get
         * duplicated PRNG output.
         */

        // NOTE: Consider inserting a BufferedInputStream between
        // DataInputStream and FileInputStream if you need higher
        // PRNG output performance and can live with future PRNG
        // output being pulled into this process prematurely.
        private val urandomIn: DataInputStream by lazy {
            engineSetSeed(generateSeed())

            try {
                URANDOM_FILE.inputStream().dataInputStream()
            } catch (e: IOException) {
                throw SecurityException("Failed to open $URANDOM_FILE for reading", e)
            }
        }

        override fun engineSetSeed(bytes: ByteArray) {
            try {
                URANDOM_FILE.outputStream().use {
                    it.write(bytes)
                    it.flush()
                }
            } catch (e: Throwable) {
                // Do nothing here. This can fail on Samsung devices and other devices
                // where /dev/urandom is not writable probably due to SELinux policies.
                // discussion here: https://plus.google.com/+AndroidDevelopers/posts/YxWzeNQMJS2
                // Although it is good practise to seed it with more entropy,
                // /dev/urandom should already be seeded.
            }
        }

        override fun engineNextBytes(bytes: ByteArray) {
            try {
                val dis: DataInputStream = urandomIn
                synchronized(dis) {
                    dis.readFully(bytes)
                }
            } catch (e: IOException) {
                throw SecurityException("Failed to read from $URANDOM_FILE", e)
            }

        }

        override fun engineGenerateSeed(size: Int): ByteArray {
            val seed = ByteArray(size)
            engineNextBytes(seed)
            return seed
        }

        private companion object {
            private val URANDOM_FILE = File(DEV_URANDOM)
        }
    }
}
