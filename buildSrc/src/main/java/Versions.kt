object Versions {
    const val kotlin = "1.7.20"
    const val buildGradle = "7.3.1"
    const val ksp = "1.7.20-1.0.8"

    const val safeArgs = "2.5.3"
    const val googleService = "4.3.15"
    const val lifeCycle = "2.5.1"
    const val coreKtx = "1.7.0"
    const val appCompat = "1.5.1"
    const val material = "1.8.0"
    const val junit = "4.13.2"
    const val junitTest = "1.1.5"
    const val espressoCore = "3.5.1"

    const val navigation = "2.5.3"

    const val retrofit = "2.9.0"
    const val moshi = "1.14.0"
    const val okHttp = "4.10.0"

    const val room = "2.4.3"

    const val dataStore = "1.0.0"

    const val hilt = "2.44"
    const val glide = "4.14.2"

    const val coroutine = "1.6.4"
    const val fragmentKtx = "1.5.5"
    const val viewPager2 = "1.0.0"
    const val paging3 = "3.1.1"

    const val firebaseBom = "31.2.1"
    const val facebookShimmer = "0.5.0"

    const val timber = "5.0.1"

    const val workManager = "2.7.0"
    const val hiltWorker = "1.0.0"
}

object Kotlin {
    const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutine}"
    const val coroutineAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
}

object AndroidX {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationSafeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycle}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val paging3 = "androidx.paging:paging-runtime-ktx:${Versions.paging3}"

    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    const val dataStorePreference =
        "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    const val dataStorePreferenceCore =
        "androidx.datastore:datastore-preferences-core:${Versions.dataStore}"

    const val workManagerRuntime = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val workerManagerHilt = "androidx.hilt:hilt-work:${Versions.hiltWorker}"
    const val workerManagerHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltWorker}"
}

object Google {
    const val googleService = "com.google.gms:google-services:${Versions.googleService}"

    const val material = "com.google.android.material:material:${Versions.material}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseConfig = "com.google.firebase:firebase-config-ktx"
    const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
    const val firebaseAuth = "com.google.firebase:firebase-auth-ktx"
}

object Library {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    const val facebookShimmer = "com.facebook.shimmer:shimmer:${Versions.facebookShimmer}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object UnitTest {
    const val junit = "junit:junit:${Versions.junit}"
}

object AndroidTest {
    const val androidJunit = "androidx.core:core-ktx:${Versions.junitTest}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
