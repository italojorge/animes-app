import dependencies.implementation
import dependencies.testDependencies

apply {
    plugin("kotlin")
}

dependencies {
    testDependencies()
    implementation(project(ProjectModules.Feature.AUTH.DOMAIN))
    implementation(project(ProjectModules.Base.DOMAIN))
}
