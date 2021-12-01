import dependencies.androidx
import dependencies.biometric
import dependencies.koinAndroid

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
}

dependencies {
    androidx()
    koinAndroid()
    biometric()
    implementation(project(ProjectModules.Base.DOMAIN))
    implementation(project(ProjectModules.Base.DATA_REMOTE))
    implementation(project(ProjectModules.Base.CORE))

    implementation(project(ProjectModules.Root.DATA_LOCAL))
    implementation(project(ProjectModules.Root.NAVIGATION))

    implementation(project(ProjectModules.Feature.AUTH.UI))
    implementation(project(ProjectModules.Feature.AUTH.DOMAIN))
    implementation(project(ProjectModules.Feature.AUTH.DATA))
    implementation(project(ProjectModules.Feature.AUTH.DATA_REMOTE))

    implementation(project(ProjectModules.Feature.HOME.UI))
    implementation(project(ProjectModules.Feature.HOME.DOMAIN))
    implementation(project(ProjectModules.Feature.HOME.DATA))
    implementation(project(ProjectModules.Feature.HOME.DATA_REMOTE))
}