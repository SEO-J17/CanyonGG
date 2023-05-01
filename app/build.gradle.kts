plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = AppConfig.appNamespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        testInstrumentationRunner = AppConfig.testRunner
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
            applicationIdSuffix = AppConfig.flavorDevIdSuffix
        }
        // 실제 배포용
        create("prod") {
            dimension = AppConfig.flavorDimension
            applicationIdSuffix = AppConfig.flavorProdIdSuffix
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    testImplementation(UnitTest.junit)
    androidTestImplementation(AndroidTest.androidJunit)
    androidTestImplementation(AndroidTest.espressoCore)

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))

    // Timber
    implementation(Library.timber)

    // okhttp
    implementation(Library.okHttp)
    implementation(Library.okHttpInterceptor)

    // Hilt
    implementation(Google.hiltAndroid)
    kapt(Google.hiltCompiler)

    // WorkManager
    implementation(AndroidX.workManagerRuntime)
    implementation(AndroidX.workerManagerHilt)
    kapt(AndroidX.workerManagerHiltCompiler)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}
