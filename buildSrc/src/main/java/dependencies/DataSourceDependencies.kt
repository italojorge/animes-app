package dependencies
import org.gradle.api.artifacts.dsl.DependencyHandler

object DataSourceDependencies {
    object Versioning {
        const val ROOM_VERSION = "2.3.0"
        const val RETROFIT_VERSION = "2.9.0"
    }

    object Libs {
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versioning.ROOM_VERSION}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versioning.ROOM_VERSION}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versioning.ROOM_VERSION}"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versioning.RETROFIT_VERSION}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versioning.RETROFIT_VERSION}"
    }
}

fun DependencyHandler.retrofit() {
    api(DataSourceDependencies.Libs.RETROFIT)
    api(DataSourceDependencies.Libs.RETROFIT_CONVERTER_GSON)
}

fun DependencyHandler.room() {
    implementation(DataSourceDependencies.Libs.ROOM_RUNTIME)
    kapt(DataSourceDependencies.Libs.ROOM_COMPILER)
    implementation(DataSourceDependencies.Libs.ROOM_KTX)
}