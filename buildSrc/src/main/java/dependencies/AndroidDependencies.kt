package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

object AndroidDependencies {
    object Versioning {
        const val APP_COMPAT_VERSION = "1.4.0"

        const val LIFECYCLE_VERSION = "2.4.0"
        const val COROUTINES_VERSION = "1.5.2"
        const val NAVIGATION_VERSION = "2.3.5"

        const val CORE_KTX_VERSION = "1.7.0"
        const val BIOMETRIC_VERSION = "1.1.0"
        const val MULTIDEX_VERSION = "2.0.1"
        const val MATERIAL_DESIGN_VERSION = "1.4.0"
    }

    object Libs {
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versioning.APP_COMPAT_VERSION}"
        const val APP_COMPAT_RESOURCES = "androidx.appcompat:appcompat-resources:${Versioning.APP_COMPAT_VERSION}"

        const val CORE_KTX = "androidx.core:core-ktx:${Versioning.CORE_KTX_VERSION}"
        const val MULTIDEX = "androidx.multidex:multidex:${Versioning.MULTIDEX_VERSION}"
        const val BIOMETRIC = "androidx.biometric:biometric:${Versioning.BIOMETRIC_VERSION}"

        const val LIFECYCLE_VIEW_MODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versioning.LIFECYCLE_VERSION}"
        const val LIFECYCLE_LIVE_DATA =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versioning.LIFECYCLE_VERSION}"
        const val LIFECYCLE_RUNTIME =
            "androidx.lifecycle:lifecycle-runtime-ktx::${Versioning.LIFECYCLE_VERSION}"

        const val NAVIGATION_FRAGMENT_KTX =
            "androidx.navigation:navigation-fragment-ktx:${Versioning.NAVIGATION_VERSION}"
        const val NAVIGATION_UI_KTX =
            "androidx.navigation:navigation-ui-ktx:${Versioning.NAVIGATION_VERSION}"

        const val MATERIAL_DESIGN = "com.google.android.material:material:${Versioning.MATERIAL_DESIGN_VERSION}"

        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versioning.COROUTINES_VERSION}"
    }
}

fun DependencyHandler.androidx() {
    implementation(AndroidDependencies.Libs.APP_COMPAT)
    implementation(AndroidDependencies.Libs.APP_COMPAT_RESOURCES)
    implementation(AndroidDependencies.Libs.CORE_KTX)
    implementation(AndroidDependencies.Libs.MULTIDEX)
    implementation(AndroidDependencies.Libs.MATERIAL_DESIGN)
}

fun DependencyHandler.lifecycle() {
    implementation(AndroidDependencies.Libs.LIFECYCLE_VIEW_MODEL)
    implementation(AndroidDependencies.Libs.LIFECYCLE_LIVE_DATA)
//    implementation(AndroidDependencies.Libs.LIFECYCLE_RUNTIME)
}

fun DependencyHandler.navigation() {
    implementation(AndroidDependencies.Libs.NAVIGATION_FRAGMENT_KTX)
    implementation(AndroidDependencies.Libs.NAVIGATION_UI_KTX)
}

fun DependencyHandler.biometric() {
    implementation(AndroidDependencies.Libs.BIOMETRIC)
}

fun DependencyHandler.coroutines() {
    api(AndroidDependencies.Libs.COROUTINES_CORE)
}
