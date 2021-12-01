import dependencies.implementation
import dependencies.paging

apply {
    plugin("kotlin")
}

dependencies {
    implementation(project(ProjectModules.Base.DOMAIN))
    implementation(project(ProjectModules.Feature.HOME.DOMAIN))
    paging()
}