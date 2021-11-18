plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
}

android {
    compileSdk = Versioning.Android.COMPILE_SDK_VERSION
    defaultConfig {
        applicationId = Config.Android.APPLICATION_ID
        minSdk = Versioning.Android.MIN_SDK_VERSION
        targetSdk = Versioning.Android.TARGET_SDK_VERSION
        versionCode = Versioning.Android.APP_VERSION_CODE
        versionName = Versioning.Android.APP_VERSION_NAME
        testInstrumentationRunner = Config.Android.TEST_INSTRUMENTATION_RUNNER
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
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
    koin()
    navigation()
}
