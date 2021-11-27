import dependencies.implementation

apply {
    plugin("kotlin")
}

dependencies {
    implementation(project(ProjectModules.Base.DOMAIN))
}
