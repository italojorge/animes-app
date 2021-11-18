apply {
    from("feature-common-module.gradle")
}

dependencies {
    api(project(":bases:base-domain"))
}