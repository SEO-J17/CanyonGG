plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
}

android {
    namespace = AppConfig.dataNamespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.testRunner
        consumerProguardFiles("consumer-rules.pro")

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas".toString())
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    flavorDimensions.add(AppConfig.flavorDimension)
    productFlavors {
        // 개발용
        create("dev") {
            dimension = AppConfig.flavorDimension
        }
        // 실제 배포용
        create("prod") {
            dimension = AppConfig.flavorDimension
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    testImplementation(UnitTest.junit)
    androidTestImplementation(AndroidTest.androidJunit)
    androidTestImplementation(AndroidTest.espressoCore)

    // Retrofit
    implementation(Library.retrofit)

    // Retrofit with Moshi Converter
    implementation(Library.retrofitScalars)
    implementation(Library.retrofitMoshi)
    implementation(Library.moshiKotlin)
    ksp(Library.moshiCodeGen)

    // okhttp
    implementation(Library.okHttp)
    implementation(Library.okHttpInterceptor)

    // Hilt
    implementation(Google.hiltAndroid)
    kapt(Google.hiltCompiler)

    // Room
    implementation(AndroidX.roomRuntime)
    implementation(AndroidX.roomKtx)
    kapt(AndroidX.roomCompiler)

    // Coroutine
    implementation(Kotlin.coroutineCore)
    implementation(Kotlin.coroutineAndroid)

    // Paging
    implementation(AndroidX.paging3)

    // Firebase Bom
    implementation(platform(Google.firebaseBom))

    // Firebase - remote config
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(Google.firebaseConfig)
    implementation(Google.firebaseAnalytics)

    // DataStore
    implementation(AndroidX.dataStorePreference)
    implementation(AndroidX.dataStorePreferenceCore)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
