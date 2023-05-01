plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    namespace = AppConfig.presentationNamespace
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(Google.material)
    testImplementation(UnitTest.junit)
    androidTestImplementation(AndroidTest.androidJunit)
    androidTestImplementation(AndroidTest.espressoCore)

    implementation(project(":domain"))

    // Navigation
    implementation(AndroidX.navigationFragment)
    implementation(AndroidX.navigationUi)

    // Hilt
    implementation(Google.hiltAndroid)
    kapt(Google.hiltCompiler)

    // Glide
    implementation(Library.glide)
    annotationProcessor(Library.glideCompiler)

    // Coroutine
    implementation(Kotlin.coroutineCore)
    implementation(Kotlin.coroutineAndroid)

    // lifecucleScope
    implementation(AndroidX.lifecycleRuntime)

    // fragment
    implementation(AndroidX.fragmentKtx)

    // viewPager2
    implementation(AndroidX.viewPager2)

    // Paging
    implementation(AndroidX.paging3)

    // Firebase Bom
    implementation(platform(Google.firebaseBom))

    // Firebase - remote config
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(Google.firebaseConfig)
    implementation(Google.firebaseAnalytics)

    // Firebase Auth
    implementation(Google.firebaseAuth)

    // loading Ui
    implementation(Library.facebookShimmer)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}
