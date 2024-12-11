plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.vadzim.yeumushkou.data"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(project(":domain"))
    implementation(project(":core:common"))
}