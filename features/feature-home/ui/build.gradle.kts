apply {
    from("feature-common-module.gradle")
}

dependencies {
    implementation(project("bases:base-ui"))
}
