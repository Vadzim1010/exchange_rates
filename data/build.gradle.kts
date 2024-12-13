plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.vadzim.yeumushkou.data"

    kapt {
        arguments {arg("room.schemaLocation", "$projectDir/schemas")}
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)

    implementation(project(":domain"))
    implementation(project(":core:common"))
}