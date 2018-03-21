object Depends {
    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    }

    object Support {
        val support_v4 = "com.android.support:support-v4:${Versions.supportLibrary}"
        val appcompat_v7 = "com.android.support:appcompat-v7:${Versions.supportLibrary}"
        val cardview_v7 = "com.android.support:cardview-v7:${Versions.supportLibrary}"
        val gridlayout_v7 = "com.android.support:gridlayout-v7:${Versions.supportLibrary}"
        val mediarouter_v7 = "com.android.support:mediarouter-v7:${Versions.supportLibrary}"
        val palette_v7 = "com.android.support:palette-v7:${Versions.supportLibrary}"
        val recyclerview_v7 = "com.android.support:recyclerview-v7:${Versions.supportLibrary}"
        val preference_v7 = "com.android.support:preference-v7:${Versions.supportLibrary}"
        val preference_v14 = "com.android.support:preference-v14:${Versions.supportLibrary}"
        val support_vector_drawable = "com.android.support:support-vector-drawable:${Versions.supportLibrary}"
        val animated_vector_drawable = "com.android.support:animated-vector-drawable:${Versions.supportLibrary}"
        val design = "com.android.support:design:${Versions.supportLibrary}"
        val customtabs = "com.android.support:customtabs:${Versions.supportLibrary}"
        val percent = "com.android.support:percent:${Versions.supportLibrary}"
        val constraint = "com.android.support.constraint:constraint-layout:1.1.0-beta5"
        val multidex = "com.android.support:multidex:${Versions.multidex}"
        val multidex_instrumentation = "com.android.support:multidex-instrumentation:${Versions.multidex}"
        val support_emoji = "com.android.support:support-emoji-appcompat:${Versions.supportLibrary}"
    }

    val ktx = "androidx.core:core-ktx:0.2"

    object OkHttp3 {
        val core = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        val loggingIntercepter = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    }

    object Retrofit {
        val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        val adapterRxJava2 = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object Kotshi {
        val api = "se.ansman.kotshi:api:${Versions.kotshi}"
        val compiler = "se.ansman.kotshi:compiler:${Versions.kotshi}"
    }

    object LifeCycle {
        val runtime = "android.arch.lifecycle:runtime:${Versions.archLifecycle}"
        val extensions = "android.arch.lifecycle:extensions:${Versions.archLifecycle}"
        val reactivestreams = "android.arch.lifecycle:reactivestreams:${Versions.archLifecycle}"
        val compiler = "android.arch.lifecycle:compiler:${Versions.archLifecycle}"
    }

    object LiveData {
        val testing = "android.arch.core:core-testing:${Versions.archLifecycle}"
    }

    object Room {
        val runtime = "android.arch.persistence.room:runtime:${Versions.archRoom}"
        val rxjava2 = "android.arch.persistence.room:rxjava2:${Versions.archRoom}"
        val compiler = "android.arch.persistence.room:compiler:${Versions.archRoom}"
        val testing = "android.arch.persistence.room:testing:${Versions.archRoom}"
    }

    object RxJava2 {
        val core = "io.reactivex.rxjava2:rxjava:2.1.10"
        val android = "io.reactivex.rxjava2:rxandroid:2.0.2"
        val kotlin = "io.reactivex.rxjava2:rxkotlin:2.2.0"
    }

    object Dagger {
        val core = "com.google.dagger:dagger:${Versions.dagger}"
        val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        val android = "com.google.dagger:dagger-android:${Versions.dagger}"
        val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object PlayService {
        val map = "com.google.android.gms:play-services-maps:${Versions.firebase}"
    }

    object Firebase {
        val firestore = "com.google.firebase:firebase-firestore:${Versions.firebase}"
        val auth = "com.google.firebase:firebase-auth:${Versions.firebase}"
        val core = "com.google.firebase:firebase-core:${Versions.firebase}"
        val messaging = "com.google.firebase:firebase-messaging:${Versions.firebase}"
    }

    object PermissionDispatcher {
        val api = "com.github.hotchemi:permissionsdispatcher:${Versions.permissionDispatcher}"
        val processor = "com.github.hotchemi:permissionsdispatcher-processor:${Versions.permissionDispatcher}"
    }

    val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.0.5"

    object Kotpref {
        val kotpref = "com.chibatching.kotpref:kotpref:${Versions.kotpref}"
        val initializer = "com.chibatching.kotpref:initializer:${Versions.kotpref}"
        val enumSupport = "com.chibatching.kotpref:enum-support:${Versions.kotpref}"
    }

    object Stetho {
        val core = "com.facebook.stetho:stetho:${Versions.stetho}"
        val okhttp3 = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"
        val timber = "com.facebook.stetho:stetho-timber:${Versions.stetho}"
    }

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:2.9.1@aar"
    val timber = "com.jakewharton.timber:timber:4.6.1"
    val leakcanary = "com.squareup.leakcanary:leakcanary-android:1.5.4"

    val debot = "com.tomoima.debot:debot:${Versions.debot}"

    val junit = "junit:junit:4.12"
    val mockitoKotlin = "com.nhaarman:mockito-kotlin:1.5.0"

    object Robolectric {
        val core = "org.robolectric:robolectric:${Versions.robolectric}"
        val support_v4 = "org.robolectric:shadows-supportv4:${Versions.robolectric}"
        val multidex = "org.robolectric:shadows-multidex:${Versions.robolectric}"
    }

    val assertk = "com.willowtreeapps.assertk:assertk:0.9"
    val threetenbp = "org.threeten:threetenbp:1.3.6"

    object SupportTest {
        val runner = "com.android.support.test:runner:1.0.1"
        val espresso = "com.android.support.test.espresso:espresso-core:3.0.1"
        val contrib = "com.android.support.test.espresso:espresso-contrib:3.0.1"
    }

    val screenshot = "com.facebook.testing.screenshot:core:0.6.0@jar"
}