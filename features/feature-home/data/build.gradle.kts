import dependencies.implementation
import dependencies.paging
import dependencies.testDependencies

apply {
    plugin("kotlin")
}

dependencies {
    implementation(project(ProjectModules.Base.DOMAIN))
    implementation(project(ProjectModules.Feature.HOME.DOMAIN))
    testDependencies()
    paging()
}