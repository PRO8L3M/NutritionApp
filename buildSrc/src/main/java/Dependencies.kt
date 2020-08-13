@file:Suppress("MayBeConstant")

object Versions {
    val kotlin = "1.3.72"
    val buildGradle = "4.0.1"

    val appCompat = "1.1.0"
    val coreKtx = "1.2.0"
    val constraintLayout = "1.1.3"

    val junit = "4.12"
    val androidxJunit = "1.1.1"
    val androidxEspresso = "3.2.0"

    val koin = "2.1.5"

    val coroutines = "1.3.5"

    val material = "1.1.0"

    val leakCanary = "2.0"

    val timber = "4.7.1"

    val legacy = "1.0.0"

    val archComponentsKtx = "2.3.0-alpha01"

    val navigation = "2.3.0-alpha01"

    val gradleVersionsPlugin = "0.28.0"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    val androidxLegacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"

    val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val androidxCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    val junit = "junit:junit:${Versions.junit}"
    val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
    val androidxEspresso = "androidx.test.espresso:espresso-core:${Versions.androidxEspresso}"

    val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    val koinAndroidxScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    val koinAndroidxViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinAndroidxFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"

    val viewModelScopeKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archComponentsKtx}"
    val lifecycleScopeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.archComponentsKtx}"
    val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.archComponentsKtx}"

    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    val navigationKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    val material = "com.google.android.material:material:${Versions.material}"

    val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val gradleVersionPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
}