plugins {
    `android-library`
    `kotlin-android`
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.vadzim.yeumushkou.domain"
}

dependencies {

    implementation(libs.androidx.core.ktx)
}