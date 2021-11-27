import dependencies.androidx
import dependencies.implementation
import dependencies.koinAndroid
import dependencies.lottieAnimations
import dependencies.navigation

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdk = ProjectConfig.Android.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = ProjectConfig.Android.APPLICATION_ID
        minSdk = ProjectConfig.Android.MIN_SDK_VERSION
        targetSdk = ProjectConfig.Android.TARGET_SDK_VERSION
        versionCode = ProjectConfig.Android.APP_VERSION_CODE
        versionName = ProjectConfig.Android.APP_VERSION_NAME
        testInstrumentationRunner = ProjectConfig.Android.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments["clearPackageData"] = "true"
        testInstrumentationRunnerArguments["runnerBuilder"] = "de.mannodermaus.junit5.AndroidJUnit5Builder"

        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isTestCoverageEnabled = true
            isDebuggable = true
        }
    }

    flavorDimensions.add("default")

    productFlavors {
        create("homolog") {
            dimension = "default"
            applicationIdSuffix = ".dev"
            versionNameSuffix = ".dev"
            buildConfigField("String", "BASE_URL", Environment.BASE_URL_DEV)
            resValue("string", "app_name", "Homolog - Animes")
        }
        create("prod") {
            dimension = "default"
            buildConfigField("String", "BASE_URL", Environment.BASE_URL_PROD)
            resValue("string", "app_name", "Animes")
        }
    }

    testOptions {
        animationsDisabled = true
    }
}

dependencies {
    androidx()
    koinAndroid()
    navigation()
    lottieAnimations()
    implementation(project(ProjectModules.Root.DI))
}
