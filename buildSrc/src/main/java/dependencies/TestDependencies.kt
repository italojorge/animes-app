package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

object TestDependencies {
    object Versioning {
        const val ESPRESSO_VERSION = "3.4.0"
        const val MOCKK_VERSION = "1.11.0"

        const val JUNIT_5_VERSION = "5.8.2"
        const val JUNIT_5_ANDROID_VERSION = "1.3.0"
        const val COROUTINES_VERSION = "1.5.2"
    }

    object Libs {
        const val JUNIT5_API = "org.junit.jupiter:junit-jupiter-api:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_ENGINE =
            "org.junit.jupiter:junit-jupiter-engine:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_PARAMS =
            "org.junit.jupiter:junit-jupiter-params:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_ANDROID_CORE =
            "de.mannodermaus.junit5:android-test-core:${Versioning.JUNIT_5_ANDROID_VERSION}"
        const val JUNIT5_ANDROID_RUNNER =
            "de.mannodermaus.junit5:android-test-runner:${Versioning.JUNIT_5_ANDROID_VERSION}"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versioning.COROUTINES_VERSION}"

        const val KOIN_TEST = "org.koin:koin-test:${DIDependencies.Versioning.KOIN_VERSION}"
        const val ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${Versioning.ESPRESSO_VERSION}"
        const val ESPRESSO_CONTRIB =
            "androidx.test.espresso:espresso-contrib:${Versioning.ESPRESSO_VERSION}"
        const val ESPRESSO_ACCESSIBILITY =
            "androidx.test.espresso:espresso-accessibility:${Versioning.ESPRESSO_VERSION}"
        const val ESPRESSO_INTENTS =
            "androidx.test.espresso:espresso-intents:${Versioning.ESPRESSO_VERSION}"
        const val MOCKK = "io.mockk:mockk:${Versioning.MOCKK_VERSION}"
        const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versioning.MOCKK_VERSION}"
        const val ANDROIDX_NAVIGATION_TESTING =
            "androidx.navigation:navigation-testing:${AndroidDependencies.Versioning.NAVIGATION_VERSION}"
    }
}

fun DependencyHandler.espresso() {
    androidTestImplementation(TestDependencies.Libs.ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.Libs.ESPRESSO_CONTRIB)
    androidTestImplementation(TestDependencies.Libs.ESPRESSO_ACCESSIBILITY)
    androidTestImplementation(TestDependencies.Libs.ESPRESSO_INTENTS)
}

fun DependencyHandler.mockk() {
    testImplementation(TestDependencies.Libs.MOCKK)
}

fun DependencyHandler.mockkAndroid() {
    androidTestImplementation(TestDependencies.Libs.MOCKK_ANDROID)
}

fun DependencyHandler.koinTest() {
    testImplementation(TestDependencies.Libs.KOIN_TEST)
    androidTestImplementation(TestDependencies.Libs.KOIN_TEST)
}

fun DependencyHandler.testDependencies() {
    coroutinesTest()
    junit5()
    mockk()
}

fun DependencyHandler.androidTestDependencies() {
    coroutinesAndroidTest()
    junit5Android()
    mockkAndroid()
}

fun DependencyHandler.coroutinesTest() {
    testImplementation(TestDependencies.Libs.COROUTINES_TEST)
}

fun DependencyHandler.coroutinesAndroidTest() {
    androidTestImplementation(TestDependencies.Libs.COROUTINES_TEST)
}

fun DependencyHandler.navigationTest() {
    androidTestImplementation(TestDependencies.Libs.ANDROIDX_NAVIGATION_TESTING)
}

fun DependencyHandler.testUtilsDependencies() {
    implementation(TestDependencies.Libs.JUNIT5_API)
    implementation(TestDependencies.Libs.JUNIT5_ENGINE)
    implementation(TestDependencies.Libs.JUNIT5_PARAMS)
}

fun DependencyHandler.junit5() {
    testImplementation(TestDependencies.Libs.JUNIT5_API)
    testRuntimeOnly(TestDependencies.Libs.JUNIT5_ENGINE)
    testImplementation(TestDependencies.Libs.JUNIT5_ENGINE)
    testImplementation(TestDependencies.Libs.JUNIT5_PARAMS)
}

fun DependencyHandler.junit5Android() {
    androidTestImplementation(TestDependencies.Libs.JUNIT5_API)
    androidTestImplementation(TestDependencies.Libs.JUNIT5_ANDROID_CORE)
    androidTestRuntimeOnly(TestDependencies.Libs.JUNIT5_ANDROID_RUNNER)
}
