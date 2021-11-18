import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

object Config {
    object BuildPlugins {
        const val ANDROID_PLUGIN =
            "com.android.tools.build:gradle:${Versioning.ANDROID_GRADLE_PLUGIN_VERSION}"
        const val KOTLIN_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versioning.KOTLIN_VERSION}"
        const val NAVIGATION_SAFE_ARGS_PLUGIN =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versioning.NAVIGATION_VERSION}"
        const val JACOCO_PLUGIN = "org.jacoco:org.jacoco.core:${Versioning.JACOCO_VERSION}"
        const val PLAY_SERVICES_PLUGIN =
            "com.google.gms:google-services:${Versioning.PLAY_SERVICES_PLUGIN_VERSION}"
        const val CRASHLYTICS_PLUGIN =
            "com.google.firebase:firebase-crashlytics-gradle:${Versioning.FIREBASE_CRASHLYTICS_GRADLE_VERSION}"
        const val PERFORMANCE_PLUGIN =
            "com.google.firebase:perf-plugin:${Versioning.FIREBASE_PERFORMANCE_GRADLE_VERSION}"
        const val APP_DISTRIBUTION =
            "com.google.firebase:firebase-appdistribution-gradle:${Versioning.APP_DISTRIBUTION}"
        const val JUNIT_5 =
            "de.mannodermaus.gradle.plugins:android-junit5:${Versioning.JUNIT_5_PLUGIN_VERSION}"
    }

    object Android {
        const val APPLICATION_ID = "br.com.meupag"
        const val TEST_INSTRUMENTATION_RUNNER = "br.com.p20.espresso.runner.EspressoRunner"
    }

    /**
     * Estamos usando versão dinâmina aqui para sempre executar a pipeline com a versão mais nova
     * da lib de conta, isso evitar problemas de integração.
     * Obs: Não usar versão fixa
     */
    object WillLibs {
        const val ACCOUNT_MODULE = "br.com.will.account:flutter_release:0.1.41"
        const val ACCOUNT_MODULE_SNAPSHOTS = "br.com.will.account:flutter_release:0.1.+"
        const val MEU_PAG_MODULE = "br.com.meupag:pag:1.60.0"
    }

    object Libs {
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
        const val CORE_KTX = "androidx.core:core-ktx:1.5.0-beta01"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.0.2"
        const val LEGACY_SUPPORT_V4 = "androidx.legacy:legacy-support-v4:1.0.0"
        const val MULTIDEX = "androidx.multidex:multidex:2.0.1"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.1.0"
        const val FRAGMENT =
            "androidx.fragment:fragment-ktx:${Versioning.ANDROIDX_FRAGMENT_VERSION}"
        const val BIOMETRIC = "androidx.biometric:biometric:1.1.0-rc01"

        const val LIFECYCLE_VIEWMODEL =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versioning.LIFECYCLE_VERSION}"
        const val LIFECYCLE_EXTENSIONS =
            "androidx.lifecycle:lifecycle-extensions:${Versioning.LIFECYCLE_VERSION}"
        const val LIFECYCLE_COMMON_JAVA8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versioning.LIFECYCLE_VERSION}"

        const val NAVIGATION_FRAGMENT_KTX =
            "androidx.navigation:navigation-fragment-ktx:${Versioning.NAVIGATION_VERSION}"
        const val NAVIGATION_UI_KTX =
            "androidx.navigation:navigation-ui-ktx:${Versioning.NAVIGATION_VERSION}"

        const val ROOM_RUNTIME = "androidx.room:room-runtime:${Versioning.ROOM_VERSION}"
        const val ROOM_COMPILER = "androidx.room:room-compiler:${Versioning.ROOM_VERSION}"
        const val ROOM_KTX = "androidx.room:room-ktx:${Versioning.ROOM_VERSION}"

        const val FIREBASE_BOM =
            "com.google.firebase:firebase-bom:${Versioning.FIREBASE_BOM_VERSION}"
        const val FIREBASE_ANALYTICS_KTX = "com.google.firebase:firebase-analytics-ktx"
        const val FIREBASE_CONFIG_KTX = "com.google.firebase:firebase-config-ktx"
        const val FIREBASE_CORE = "com.google.firebase:firebase-core"
        const val FIREBASE_CRASHLYTICS = "com.google.firebase:firebase-crashlytics"
        const val FIREBASE_MESSAGING = "com.google.firebase:firebase-messaging"
        const val FIREBASE_VISION = "com.google.android.gms:play-services-vision:20.1.1"
        const val FIREBASE_VISION_COMMON =
            "com.google.android.gms:play-services-vision-common:19.1.1"
        const val FIREBASE_PERFORMANCE = "com.google.firebase:firebase-perf-ktx"

        const val MATERIAL_DESIGN = "com.google.android.material:material:1.3.0"

        const val PLAY_SERVICES_AUTH = "com.google.android.gms:play-services-auth:18.1.0"
        const val PLAY_SERVICES_AUTH_API_PHONE =
            "com.google.android.gms:play-services-auth-api-phone:17.4.0"
        const val PLAY_SERVICES_LOCATION = "com.google.android.gms:play-services-location:17.1.0"
        const val PLAY_SERVICES_ADS_IDENTIFIER =
            "com.google.android.gms:play-services-ads-identifier:17.0.0"
        const val PLAY_CORE = "com.google.android.play:core:${Versioning.PLAY_CORE}"

        const val KOTLIN_JAVA8 =
            "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versioning.KOTLIN_VERSION}"
        const val KOTLIN_REFLECT =
            "org.jetbrains.kotlin:kotlin-reflect:${Versioning.KOTLIN_VERSION}"
        const val COROUTINES_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versioning.COROUTINES_VERSION}"

        const val KOIN_CORE = "org.koin:koin-core:${Versioning.KOIN_VERSION}"
        const val KOIN_VIEW_MODEL = "org.koin:koin-androidx-viewmodel:${Versioning.KOIN_VERSION}"

        const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versioning.RETROFIT_VERSION}"
        const val RETROFIT_CONVERTER_MOSHI =
            "com.squareup.retrofit2:converter-moshi:${Versioning.RETROFIT_VERSION}"
        const val RETROFIT_CONVERTER_GSON =
            "com.squareup.retrofit2:converter-gson:${Versioning.RETROFIT_VERSION}"
        const val MOSHI = "com.squareup.moshi:moshi:${Versioning.MOSHI_VERSION}"
        const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versioning.MOSHI_VERSION}"
        const val OKHTTP3_LOGGING_INTERCEPTOR =
            "com.squareup.okhttp3:logging-interceptor:${Versioning.OKHTTP3_LOGGING_INTERCEPTOR_VERSION}" //não alterar a versão problema com API 16

        const val AWS_ANDROID_SDK = "com.amazonaws:aws-android-sdk-s3:${Versioning.AWS_VERSION}"
        const val AWS_ANDROID_SDK_MOBILE_CLIENT =
            "com.amazonaws:aws-android-sdk-mobile-client:${Versioning.AWS_VERSION}"

        const val COMMONS_IO = "commons-io:commons-io:2.5"
        const val EXPANDABLE_RECYCLER_VIEW = "com.thoughtbot:expandablerecyclerview:1.3"
        const val INFOBIP_MESSAGING =
            "org.infobip.mobile.messaging.api:infobip-mobile-messaging-android-sdk:5.0.1@aar"
        const val PICASSO = "com.squareup.picasso:picasso:${Versioning.PICASSO_VERSION}"
        const val ANDROID_IMAGE_CROPPER = "com.theartofdev.edmodo:android-image-cropper:2.8.0"
        const val LOTTIE = "com.airbnb.android:lottie:3.1.0"
        const val FOTOAPPARAT = "io.fotoapparat:fotoapparat:2.7.0"
        const val ANDROID_SPIN_KIT = "com.github.ybq:Android-SpinKit:1.4.0"
        const val APPSFLYER = "com.appsflyer:af-android-sdk:5.4.4"
        const val INSTALL_REFERRES = "com.android.installreferrer:installreferrer:2.1"
        const val BCPROV = "org.bouncycastle:bcprov-jdk16:1.46"
        const val DEBUG_DB = "com.amitshekhar.android:debug-db:1.0.6"
        const val VIEW_PAGER_DOT_INDICATOR = "com.tbuonomo.andrui:viewpagerdotsindicator:4.1.2"
        const val SHAPE_OF_VIEW = "com.github.florent37:shapeofview:1.4.7"
        const val CHART_VIEW = "com.ramijemli.percentagechartview:percentagechartview:0.3.0"
        const val HELP_SHIFT = "com.helpshift:android-helpshift-aar:7.9.0"
        const val SHIMMER = "com.facebook.shimmer:shimmer:0.5.0"
        const val SWIPE_REFRESH = "com.reginald.swiperefresh:library:1.1.2"
        const val CHUCK_DEBUG = "com.readystatesoftware.chuck:library:1.1.0"
        const val CHUCK_RELEASE_NO_OP = "com.readystatesoftware.chuck:library-no-op:1.1.0"
        const val BROWSER = "androidx.browser:browser:1.3.0"

        const val INLOCO_ENGAGEMENT =
            "com.inlocomedia.android:android-sdk-engagement:${Versioning.INLOCO_ENGAGEMENT}"
        const val OITITEC_LIVENESS = "br.com.oititec:liveness-sdk:2.1.0"
        const val GOOGLE_GSON = "com.google.code.gson:gson:${Versioning.GOOGLE_GSON}"
    }

    object TestLibs {
        const val JUNIT5_API = "org.junit.jupiter:junit-jupiter-api:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_ENGINE =
            "org.junit.jupiter:junit-jupiter-engine:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_PARAMS =
            "org.junit.jupiter:junit-jupiter-params:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_VINTAGE =
            "org.junit.vintage:junit-vintage-engine:${Versioning.JUNIT_5_VERSION}"
        const val JUNIT5_ANDROID_CORE =
            "de.mannodermaus.junit5:android-test-core:${Versioning.JUNIT_5_ANDROID_VERSION}"
        const val JUNIT5_ANDROID_RUNNER =
            "de.mannodermaus.junit5:android-test-runner:${Versioning.JUNIT_5_ANDROID_VERSION}"

        const val JUNIT4 = "junit:junit:${Versioning.JUNIT_4_VERSION}"
        const val KOIN_TEST = "org.koin:koin-test:${Versioning.KOIN_VERSION}"
        const val COROUTINES_TEST =
            "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versioning.COROUTINES_VERSION}"
        const val ESPRESSO_CORE =
            "androidx.test.espresso:espresso-core:${Versioning.ESPRESSO_VERSION}"
        const val ESPRESSO_CONTRIB =
            "androidx.test.espresso:espresso-contrib:${Versioning.ESPRESSO_VERSION}"
        const val ESPRESSO_ACCESSIBILITY =
            "androidx.test.espresso:espresso-accessibility:${Versioning.ESPRESSO_VERSION}"
        const val ESPRESSO_INTENTS =
            "androidx.test.espresso:espresso-intents:${Versioning.ESPRESSO_VERSION}"
        const val MOCKK = "io.mockk:mockk:${Versioning.MOCKK_VERSION}"
        const val MOCKK_ANDROID = "io.mockk:mockk-android:${Versioning.MOCKK_VERSION}"
        const val ANDROIDX_TEST_EXT_JUNIT = "androidx.test.ext:junit-ktx:1.1.2"
        const val ANDROIDX_TEST_RUNNER = "androidx.test:runner:${Versioning.ANDROID_TEST_VERSION}"
        const val ANDROIDX_TEST_RULES = "androidx.test:rules:${Versioning.ANDROID_TEST_VERSION}"
        const val ANDROIDX_TEST_CORE = "androidx.test:core-ktx:${Versioning.ANDROID_TEST_VERSION}"
        const val ANDROIDX_ARCH_CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
        const val ANDROIDX_TEST_ORCHESTRATOR =
            "androidx.test:orchestrator:${Versioning.ANDROID_TEST_VERSION}"
        const val ANDROIDX_FRAGMENT_TESTING =
            "androidx.fragment:fragment-testing:${Versioning.ANDROIDX_FRAGMENT_VERSION}"
        const val ANDROIDX_NAVIGATION_TESTING =
            "androidx.navigation:navigation-testing:${Versioning.NAVIGATION_VERSION}"
        const val MOCK_WEB_SERVER =
            "com.squareup.okhttp3:mockwebserver:${Versioning.OKHTTP3_LOGGING_INTERCEPTOR_VERSION}"
        const val TRUTH = "com.google.truth:truth:1.1"
        const val JSON = "org.json:json:20180130"
    }

    object UIKitLibs {
        const val ROBOLECTRIC = "org.robolectric:robolectric:4.4"
    }
}

fun DependencyHandler.will() {
    releaseImplementation(Config.WillLibs.ACCOUNT_MODULE)
    debugImplementation(Config.WillLibs.ACCOUNT_MODULE_SNAPSHOTS)
}

fun DependencyHandler.androidx() {
    api(Config.Libs.APPCOMPAT)
    api(Config.Libs.CORE_KTX)
    api(Config.Libs.CONSTRAINT_LAYOUT)
    api(Config.Libs.LEGACY_SUPPORT_V4)
    api(Config.Libs.MULTIDEX)
    api(Config.Libs.RECYCLER_VIEW)
    api(Config.Libs.FRAGMENT)
}

fun DependencyHandler.lifecycle() {
    api(Config.Libs.LIFECYCLE_VIEWMODEL)
    api(Config.Libs.LIFECYCLE_EXTENSIONS)
    api(Config.Libs.LIFECYCLE_COMMON_JAVA8)
}

fun DependencyHandler.firebase() {
    implementation(Config.Libs.FIREBASE_ANALYTICS_KTX)
    implementation(Config.Libs.FIREBASE_CONFIG_KTX)
    implementation(Config.Libs.FIREBASE_CORE)
    implementation(Config.Libs.FIREBASE_MESSAGING)
    implementation(Config.Libs.FIREBASE_VISION)
    implementation(Config.Libs.FIREBASE_VISION_COMMON)
    implementation(Config.Libs.FIREBASE_PERFORMANCE)
    firebaseCrashlytics()
}

fun DependencyHandler.firebaseCrashlytics() {
    implementation(platform(Config.Libs.FIREBASE_BOM))
    implementation(Config.Libs.FIREBASE_CRASHLYTICS)
}

fun DependencyHandler.navigation() {
    api(Config.Libs.NAVIGATION_FRAGMENT_KTX)
    api(Config.Libs.NAVIGATION_UI_KTX)
}

fun DependencyHandler.navigationTestDependency() {
    androidTestImplementation(Config.TestLibs.ANDROIDX_NAVIGATION_TESTING)
}

fun DependencyHandler.room() {
    implementation(Config.Libs.ROOM_RUNTIME)
    kapt(Config.Libs.ROOM_COMPILER)
    implementation(Config.Libs.ROOM_KTX)
}

fun DependencyHandler.playServices() {
    api(Config.Libs.PLAY_SERVICES_AUTH)
    api(Config.Libs.PLAY_SERVICES_AUTH_API_PHONE)
    api(Config.Libs.PLAY_SERVICES_LOCATION)
}

fun DependencyHandler.playCore() {
    api(Config.Libs.PLAY_CORE)
}

fun DependencyHandler.biometric() {
    implementation(Config.Libs.BIOMETRIC)
}

fun DependencyHandler.koin() {
    api(Config.Libs.KOIN_CORE)
    api(Config.Libs.KOIN_VIEW_MODEL)
}

fun DependencyHandler.koinTestDependency() {
    testImplementation(Config.TestLibs.KOIN_TEST)
    androidTestImplementation(Config.TestLibs.KOIN_TEST)
}

fun DependencyHandler.kotlin() {
    api(Config.Libs.KOTLIN_JAVA8)
    api(Config.Libs.KOTLIN_REFLECT)
}

fun DependencyHandler.coroutines() {
    api(Config.Libs.COROUTINES_CORE)
}

fun DependencyHandler.coroutinesTestDependency() {
    testImplementation(Config.TestLibs.COROUTINES_TEST)
    androidTestImplementation(Config.TestLibs.COROUTINES_TEST)
}

fun DependencyHandler.retrofit() {
    api(Config.Libs.RETROFIT)
    api(Config.Libs.RETROFIT_CONVERTER_MOSHI)
    api(Config.Libs.RETROFIT_CONVERTER_GSON)
}

fun DependencyHandler.retrofitTestDependency() {
    androidTestImplementation(Config.TestLibs.MOCK_WEB_SERVER)
}

fun DependencyHandler.moshi() {
    api(Config.Libs.MOSHI)
    api(Config.Libs.MOSHI_KOTLIN)
}

fun DependencyHandler.espresso() {
    androidTestImplementation(Config.TestLibs.ESPRESSO_CORE)
    androidTestImplementation(Config.TestLibs.ESPRESSO_CONTRIB)
    androidTestImplementation(Config.TestLibs.ESPRESSO_ACCESSIBILITY)
    androidTestImplementation(Config.TestLibs.ESPRESSO_INTENTS)
}

fun DependencyHandler.mockk() {
    testImplementation(Config.TestLibs.MOCKK)
    androidTestImplementation(Config.TestLibs.MOCKK_ANDROID)
}

fun DependencyHandler.androidxTest() {
    testImplementation(Config.TestLibs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Config.TestLibs.ANDROIDX_TEST_EXT_JUNIT)
    androidTestImplementation(Config.TestLibs.ANDROIDX_TEST_RUNNER)
    androidTestImplementation(Config.TestLibs.ANDROIDX_TEST_RULES)
    debugImplementation(Config.TestLibs.ANDROIDX_TEST_CORE)
    debugImplementation(Config.TestLibs.ANDROIDX_ARCH_CORE_TESTING)
    debugImplementation(Config.TestLibs.ANDROIDX_FRAGMENT_TESTING)
}

fun DependencyHandler.inloco() {
    implementation(Config.Libs.PLAY_SERVICES_LOCATION)
    implementation(Config.Libs.PLAY_SERVICES_ADS_IDENTIFIER)
    implementation(Config.Libs.INLOCO_ENGAGEMENT)
}

fun DependencyHandler.junit5() {
    testImplementation(Config.TestLibs.JUNIT5_API)
    testRuntimeOnly(Config.TestLibs.JUNIT5_ENGINE)
    testImplementation(Config.TestLibs.JUNIT5_PARAMS)
    testRuntimeOnly(Config.TestLibs.JUNIT5_VINTAGE)

    androidTestImplementation(Config.TestLibs.JUNIT5_API)
    androidTestImplementation(Config.TestLibs.JUNIT5_ANDROID_CORE)
    androidTestRuntimeOnly(Config.TestLibs.JUNIT5_ANDROID_RUNNER)
}

fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

fun DependencyHandler.implementation(depName: Dependency) {
    add("implementation", depName)
}

fun DependencyHandler.releaseImplementation(depName: String) {
    add("releaseImplementation", depName)
}

fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}

fun DependencyHandler.api(depName: String) {
    add("api", depName)
}

fun DependencyHandler.api(depName: Dependency) {
    add("implementation", depName)
}

fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

fun DependencyHandler.testRuntimeOnly(depName: String) {
    add("testRuntimeOnly", depName)
}

fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

fun DependencyHandler.androidTestRuntimeOnly(depName: String) {
    add("androidTestRuntimeOnly", depName)
}
