buildscript {
    repositories {
        google()
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
    }

    dependencies {
        classpath(Config.BuildPlugins.ANDROID_PLUGIN)
        classpath(Config.BuildPlugins.KOTLIN_PLUGIN)
        classpath(Config.BuildPlugins.NAVIGATION_SAFE_ARGS_PLUGIN)
        classpath(Config.BuildPlugins.JUNIT_5)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
