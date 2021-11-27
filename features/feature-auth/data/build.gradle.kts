import dependencies.implementation
import dependencies.okHttpInterceptor

apply {
    plugin("kotlin")
}

dependencies {
    okHttpInterceptor()
    implementation(project(ProjectModules.Feature.AUTH.DOMAIN))
    implementation(project(ProjectModules.Base.DOMAIN))
}