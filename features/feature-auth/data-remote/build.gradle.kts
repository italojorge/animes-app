import dependencies.implementation
import dependencies.koin
import dependencies.okHttpInterceptor
import dependencies.retrofit
import dependencies.testDependencies

apply {
    plugin("kotlin")
}

dependencies {
    testDependencies()
    retrofit()
    koin()
    okHttpInterceptor()
    implementation(project(ProjectModules.Base.DOMAIN))
    implementation(project(ProjectModules.Base.DATA_REMOTE))
    implementation(project(ProjectModules.Feature.AUTH.DOMAIN))
    implementation(project(ProjectModules.Feature.AUTH.DATA))
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }
}