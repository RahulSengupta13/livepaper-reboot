package com.rahulsengupta.buildsrc

object AppMetaData {
    const val id = "com.rahulsengupta.livepaper"
    const val compileSdkVersion = 29
    const val targetSdkVersion = 29
    const val minSdkVersion = 24
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "29.0.2"
}

object Versions {
    // Application
    const val kotlin = "1.4.30"
    const val kotlinSerialization = "1.1.0-RC"
    const val kotlinSerializationJson = "1.0.1"
    const val gradle = "4.2.0-beta04"

    //core
    const val appcompat = "1.1.0-beta01"
    const val annotation = "1.1.0"
    const val constraintLayout = "2.0.0-beta6"
    const val ktx = "1.2.0"
    const val fragment = "1.2.1"
    const val lifecycle = "2.2.0"
    const val navigation = "2.3.0"
    const val recyclerView = "1.2.0-alpha05"
    const val material = "1.2.0-alpha05"
    const val coroutines = "1.3.3"
    const val timber = "4.7.1"
    const val circularImageView = "4.2.0"
    const val threeTen = "1.2.3"
    const val preference = "1.1.1"

    const val palette = "1.0.0"
    const val swipeRefresh = "1.2.0-alpha01"

    //database
    const val room = "2.3.0-beta01"
    const val paging = "3.0.0-beta01"

    //networking
    const val retrofit = "2.9.0"
    const val okhttp = "4.8.1"
    const val kotlinSerializationConverter = "0.7.0"

    //di
    const val daggerHilt = "2.32-alpha"
    const val daggerHiltJetpack = "1.0.0-alpha03"

    //image
    const val coil = "1.1.1"

    //testing
    const val junit = "4.12"
    const val androidxJunit = "1.1.1"
    const val espresso = "3.2.0"

}

object Dependencies {

    //kapt
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"

    //core
    const val appcompat =  "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"
    const val constraintlayout =  "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val core =  "androidx.core:core-ktx:${Versions.ktx}"
    const val fragmentKtx =  "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val lifecycleExtensions =  "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val livedata =  "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewmodel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    //navigation
    const val navigationFragment =  "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi =  "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val recyclerview =  "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

    const val palette = "androidx.palette:palette:${Versions.palette}"
    const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"

    const val roomRuntime =  "androidx.room:room-runtime:${Versions.room}"
    const val pagingRuntime =  "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    const val roomKtx =  "androidx.room:room-ktx:${Versions.room}"
    const val material =  "com.google.android.material:material:${Versions.material}"
    const val kotlinStdlib =  "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    const val kotlinSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinSerialization}"
    const val kotlinSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerializationJson}"

    const val coroutinesAndroid =  "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutinesCore =  "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val preference =  "androidx.preference:preference:${Versions.preference}"

    // Tools
    const val timber =  "com.jakewharton.timber:timber:${Versions.timber}"
    const val circularImageView =  "com.mikhaellopez:circularimageview:${Versions.circularImageView}"
    const val threeTen = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTen}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"

    // Dagger
    const val daggerHiltAndroid = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.daggerHilt}"
    const val daggerHiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHiltJetpack}"
    const val androidxHiltCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHiltJetpack}"

    // Networking
    const val retrofit =  "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okhttp =  "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLoggingInterceptor =  "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val kotlinSerializationConverter =  "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.kotlinSerializationConverter}"

    //testing
    const val junit = "junit:junit:${Versions.junit}"
    const val junitAndroidx = "androidx.test.ext:junit:${Versions.androidxJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object GradleTemplates {
    const val androidLibrary = "template-android-library.gradle"
    const val featureModule = "template-feature-module.gradle"
}

object Modules {
    const val app = ":app"
    const val persistence = ":persistence"
    const val network = ":network"
    const val home = ":home"
    const val core = ":core"
}