object ProjectConfig {
    const val teste = ":app"

    const val KOTLIN_VERSION = "1.6.0"

    object BuildPlugins {
        object Versioning {
            const val ANDROID_GRADLE_PLUGIN_VERSION = "7.0.3"
            const val NAVIGATION_VERSION = "2.3.5"
            const val JUNIT_5_PLUGIN_VERSION = "1.8.0.0"
        }

        const val ANDROID_PLUGIN =
            "com.android.tools.build:gradle:${Versioning.ANDROID_GRADLE_PLUGIN_VERSION}"
        const val KOTLIN_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
        const val NAVIGATION_SAFE_ARGS_PLUGIN =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versioning.NAVIGATION_VERSION}"
        const val JUNIT_5 =
            "de.mannodermaus.gradle.plugins:android-junit5:${Versioning.JUNIT_5_PLUGIN_VERSION}"

    }

    object Android {
        const val APPLICATION_ID = "br.com.animes"
        const val TEST_INSTRUMENTATION_RUNNER = "br.com.p20.espresso.runner.EspressoRunner"

        const val COMPILE_SDK_VERSION = 31
        const val MIN_SDK_VERSION = 21
        const val TARGET_SDK_VERSION = 31
        const val APP_VERSION_CODE = 1
        const val APP_VERSION_NAME = "0.1.0"
    }
}
