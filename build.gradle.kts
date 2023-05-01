// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(AndroidX.navigationSafeArgs)
        classpath(Google.googleService)
    }
}

plugins {
    id("com.android.application") version Versions.buildGradle apply false
    id("com.android.library") version Versions.buildGradle apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.google.dagger.hilt.android") version Versions.hilt apply false
    id("com.google.devtools.ksp") version Versions.ksp apply false
}
