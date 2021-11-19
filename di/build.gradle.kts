apply from: "../android-library-module.gradle"

dependencies {
    implementation(project(':navigation'))
    implementation(project(':bases:base-ui')
    implementation(project(':features:feature-auth')
    implementation(project(':bases:base-domain')
    implementation(project(':data')

    implementation platform(firebaseBoM)
    diDependencies.each { add(it.configuration, it.dependency, it.option) }
}
