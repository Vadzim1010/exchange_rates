plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

apply<MainGradlePlugin>()

android {
    namespace = "com.vadzim.yeumushkou"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.dagger)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    kapt(libs.dagger.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)

    implementation(project(":core:common"))
    implementation(project(":core:network"))

    implementation(project(":feature:main:root"))
    implementation(project(":feature:main:currancies"))
    implementation(project(":feature:main:favorite"))
    implementation(project(":data"))
    implementation(project(":domain"))
}