import dependencies.implementation
import dependencies.testDependencies

apply {
    plugin("kotlin")
}

dependencies {
    testDependencies()
    implementation(project(ProjectModules.Base.DOMAIN))
}
