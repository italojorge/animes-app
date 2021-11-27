package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler

object DIDependencies {
    object Versioning {
        const val KOIN_VERSION = "3.1.4"
    }

    object Libs {
        const val KOIN_CORE = "io.insert-koin:koin-core:${Versioning.KOIN_VERSION}"
        const val KOIN_ANDROID = "io.insert-koin:koin-android:${Versioning.KOIN_VERSION}"
    }
}

fun DependencyHandler.koinAndroid() {
    implementation(DIDependencies.Libs.KOIN_CORE)
    implementation(DIDependencies.Libs.KOIN_ANDROID)
}


fun DependencyHandler.koin() {
    implementation(DIDependencies.Libs.KOIN_CORE)
}