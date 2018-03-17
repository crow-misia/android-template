import com.android.build.gradle.api.ApplicationVariant
import org.jetbrains.kotlin.build.serializeToPlainText
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    id("com.android.application") version Versions.gradleBuildTool
    kotlin("android") version Versions.kotlin
    kotlin("android.extensions") version Versions.kotlin
    kotlin("kapt") version Versions.kotlin
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintGradle
//    id("io.fabric") version Versions.fabricGradleTool
    id("com.github.ben-manes.versions") version Versions.gradleVersions
    id("com.google.gms.google-services") version Versions.googleServices apply false
}

// Manifest version
val versionMajor = 1
val versionMinor = 0
val versionPatch = 0

android {
    compileSdkVersion(Versions.compileSdk)
    buildToolsVersion(Versions.buildTools)

    defaultConfig {
        applicationId = "io.github.crowmisia.template"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        versionName = "$versionMajor.$versionMinor.$versionPatch"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
    lintOptions {
        setLintConfig(file("lint.xml"))
        textReport = true
        textOutput("stdout")
    }
}

kapt {
    useBuildCache = true
}

dependencies {

    // Kotlin
    implementation(Depends.Kotlin.stdlib)
    implementation(Depends.ktx)

//==================== Support Library ====================
    implementation(Depends.Support.support_v4)
    implementation(Depends.Support.appcompat_v7)
    implementation(Depends.Support.design)
    implementation(Depends.Support.cardview_v7)
    implementation(Depends.Support.customtabs)
    implementation(Depends.Support.constraint)
    implementation(Depends.Support.multidex)
    implementation(Depends.Support.support_emoji)
    implementation(Depends.Support.preference_v7)
    implementation(Depends.Support.preference_v14)

//==================== Network ====================
    implementation(Depends.OkHttp3.core)
    implementation(Depends.OkHttp3.loggingIntercepter)

    implementation(Depends.Retrofit.core)
    implementation(Depends.Retrofit.converterMoshi)
    implementation(Depends.Retrofit.adapterRxJava2)

//==================== Structure ====================
    implementation(Depends.Kotshi.api)
    kapt(Depends.Kotshi.compiler)

    implementation(Depends.LifeCycle.runtime)
    implementation(Depends.LifeCycle.extensions)
    implementation(Depends.LifeCycle.reactivestreams)
    kapt(Depends.LifeCycle.compiler)
    implementation(Depends.Room.runtime)
    implementation(Depends.Room.rxjava2)
    kapt(Depends.Room.compiler)

    implementation(Depends.RxJava2.core)
    implementation(Depends.RxJava2.android)
    implementation(Depends.RxJava2.kotlin)

    implementation(Depends.Dagger.core)
    implementation(Depends.Dagger.android)
    implementation(Depends.Dagger.androidSupport)
    kapt(Depends.Dagger.compiler)
    kapt(Depends.Dagger.androidProcessor)

    implementation(Depends.PlayService.map)

    implementation(Depends.Firebase.core)
    implementation(Depends.Firebase.messaging)

    implementation(Depends.threetenabp)

    implementation(Depends.Kotpref.kotpref)
    implementation(Depends.Kotpref.initializer)
    implementation(Depends.Kotpref.enumSupport)

    implementation(Depends.PermissionDispatcher.api)
    kapt(Depends.PermissionDispatcher.processor)

//==================== UI ====================

//==================== Debug ====================
    debugImplementation(Depends.Stetho.core)
    debugImplementation(Depends.Stetho.okhttp3)
    debugImplementation(Depends.Stetho.timber)

    implementation(Depends.crashlytics) {
        isTransitive = true
    }

    implementation(Depends.timber)

    debugImplementation(Depends.leakcanary)

    debugImplementation(Depends.debot)

//==================== Test ====================
    testImplementation(Depends.junit)
    testImplementation(Depends.mockitoKotlin)

    testImplementation(Depends.Robolectric.core)
    testImplementation(Depends.Robolectric.support_v4)
    testImplementation(Depends.Robolectric.multidex)
    testImplementation(Depends.assertk)

    testImplementation(Depends.threetenbp)

    testImplementation(Depends.LiveData.testing)
    testImplementation(Depends.Room.testing)

    androidTestImplementation(Depends.SupportTest.runner)
    androidTestImplementation(Depends.SupportTest.espresso)
    androidTestImplementation(Depends.SupportTest.contrib)
    androidTestImplementation(Depends.Support.multidex_instrumentation)
    androidTestImplementation(Depends.assertk)
}

repositories {
    jcenter()
    google()
    mavenCentral()
}

ktlint {
    version = Versions.ktlint
    android = true
    reporters = arrayOf(ReporterType.CHECKSTYLE)
    ignoreFailures = true
}
