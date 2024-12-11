plugins {
    `android-library`
    `kotlin-android`
    `kotlin-kapt`
}

apply<LibraryGradlePlugin>()

android {
    namespace = "com.vadzim.yeumushkou.main.currancies"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}

dependencies {

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation( libs.androidx.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.fragment)
    implementation(libs.material)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.dagger)
    implementation(project(":domain"))
    kapt(libs.dagger.compiler)

    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)
    implementation(libs.okhttp)

    implementation(libs.jjwt.api)
    implementation(libs.jjwt.impl)

    implementation(project(":core:common"))
}