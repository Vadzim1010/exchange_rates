plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.vadzim.yeumushkou.core.network"
}

dependencies {

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)
    implementation(project(":core:common"))
}