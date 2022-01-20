import dependencies.coroutines
import dependencies.implementation
import dependencies.paging
import dependencies.testDependencies

apply {
    plugin("kotlin")
}

dependencies {
    testDependencies()
    implementation(project(ProjectModules.Base.DOMAIN))
    paging()
    coroutines()
}
