apply{
    from("../../../feature-common-module.gradle")
    plugin("kotlin-android")
}


dependencies {
    biometric()

    implementation(project(":bases:base-ui"))
}
