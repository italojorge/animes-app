import dependencies.implementation
import dependencies.koin
import dependencies.retrofit
import dependencies.testDependencies

apply {
    plugin("kotlin")
}

dependencies {
    testDependencies()
    koin()
    retrofit()
    implementation(project(ProjectModules.Base.DOMAIN))
    implementation(project(ProjectModules.Base.DATA_REMOTE))
    implementation(project(ProjectModules.Feature.HOME.DOMAIN))
    implementation(project(ProjectModules.Feature.HOME.DATA))
}