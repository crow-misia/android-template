@file:Suppress("NOTHING_TO_INLINE")

package app.extensions

import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream

inline fun InputStream.dataInputStream() = DataInputStream(this)

inline fun OutputStream.dataOutputStream() = DataOutputStream(this)
