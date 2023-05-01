plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = AppConfig.domainNamespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.testRunner
        consumerProguardFiles("consumer-rules.pro")
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

    testImplementation(UnitTest.junit)
    androidTestImplementation(AndroidTest.androidJunit)
    androidTestImplementation(AndroidTest.espressoCore)

    implementation(project(":data"))

    // Hilt
    implementation(Google.hiltAndroid)
    kapt(Google.hiltCompiler)

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

    // Firebase Auth
    implementation(Google.firebaseAuth)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}
