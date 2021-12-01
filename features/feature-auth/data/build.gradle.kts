import dependencies.implementation

apply {
    plugin("kotlin")
}

dependencies {
    implementation(project(ProjectModules.Feature.AUTH.DOMAIN))
    implementation(project(ProjectModules.Base.DOMAIN))
}