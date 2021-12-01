import dependencies.coroutines
import dependencies.implementation
import dependencies.paging

apply {
    plugin("kotlin")
}

dependencies {
    implementation(project(ProjectModules.Base.DOMAIN))
    paging()
    coroutines()
}
