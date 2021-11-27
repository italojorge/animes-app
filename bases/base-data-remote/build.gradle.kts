import dependencies.implementation
import dependencies.koin
import dependencies.okHttpInterceptor
import dependencies.retrofit

apply {
    plugin("kotlin")
}

dependencies {
    koin()
    retrofit()
    okHttpInterceptor()
    implementation(project(ProjectModules.Base.DOMAIN))
}