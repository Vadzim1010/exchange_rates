import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project

class LibraryGradlePlugin : Plugin<Project> {

    private val Project.libraryExtension: LibraryExtension get() = extensions.getByType(LibraryExtension::class.java)

    override fun apply(project: Project) {
        applyPlugins(project)
        setProjectConfig(project)
    }

    private fun applyPlugins(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
            plugin("kotlin-kapt")
        }
    }

    private fun setProjectConfig(project: Project) {
        project.libraryExtension.apply {
            compileSdk = ProjectConfig.COMPILE_SDK

            defaultConfig {
                minSdk = ProjectConfig.MIN_SDK
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }
        }
    }
}