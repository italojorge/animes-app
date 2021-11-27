import dependencies.androidx
import dependencies.biometric
import dependencies.implementation
import dependencies.koinAndroid
import dependencies.lifecycle

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = ProjectConfig.Android.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = ProjectConfig.Android.MIN_SDK_VERSION
        targetSdk = ProjectConfig.Android.TARGET_SDK_VERSION
        testInstrumentationRunner = ProjectConfig.Android.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"

        multiDexEnabled = true
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(project(ProjectModules.Base.CORE))
    implementation(project(ProjectModules.Feature.AUTH.DOMAIN))
    implementation(project(ProjectModules.Base.DOMAIN))
    koinAndroid()
    androidx()
    lifecycle()
    biometric()
}