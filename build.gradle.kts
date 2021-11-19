buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ProjectConfig.BuildPlugins.ANDROID_PLUGIN)
        classpath(ProjectConfig.BuildPlugins.KOTLIN_PLUGIN)
        classpath(ProjectConfig.BuildPlugins.NAVIGATION_SAFE_ARGS_PLUGIN)
        classpath(ProjectConfig.BuildPlugins.JUNIT_5)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
