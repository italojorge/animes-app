package dependencies
import dependencies.DataSourceDependencies.Libs.GSON
import dependencies.DataSourceDependencies.Libs.RETROFIT
import dependencies.DataSourceDependencies.Libs.RETROFIT_CONVERTER_GSON
import dependencies.DataSourceDependencies.Libs.OK_HTTP_INTERCEPTOR
import dependencies.DataSourceDependencies.Libs.ROOM_COMPILER
import dependencies.DataSourceDependencies.Libs.ROOM_KTX
import dependencies.DataSourceDependencies.Libs.ROOM_RUNTIME
import org.gradle.api.artifacts.dsl.DependencyHandler

object DataSourceDependencies {
    object Versioning {
        const val ROOM_VERSION = "2.3.0"
        const val RETROFIT_VERSION = "2.9.0"
        const val OK_HTTP_INTERCEPTOR_VERSION = "4.9.3"
        const val GSON_VERSION = "2.8.6"
    }

    object Libs {
        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versioning.ROOM_VERSION}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versioning.ROOM_VERSION}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versioning.ROOM_VERSION}"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versioning.RETROFIT_VERSION}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versioning.RETROFIT_VERSION}"
        const val OK_HTTP_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versioning.OK_HTTP_INTERCEPTOR_VERSION}"
        const val GSON = "com.google.code.gson:gson:${Versioning.GSON_VERSION}"
    }
}

fun DependencyHandler.retrofit() {
    implementation(RETROFIT)
    implementation(RETROFIT_CONVERTER_GSON)
}

fun DependencyHandler.gson() {
    implementation(GSON)
}

fun DependencyHandler.okHttpInterceptor() {
    implementation(OK_HTTP_INTERCEPTOR)
}

fun DependencyHandler.room() {
    implementation(ROOM_RUNTIME)
    kapt(ROOM_COMPILER)
    implementation(ROOM_KTX)
}