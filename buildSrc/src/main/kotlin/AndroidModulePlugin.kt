import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }
            configureBuildTypeForModule()
            coreDependencies()
            testDependencies()
        }
    }
}

internal fun Project.configureBuildTypeForModule() {
    extensions.configure<LibraryExtension> {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = true
            }

            getByName("debug") {
                enableUnitTestCoverage = true
                enableAndroidTestCoverage = true
            }
        }
    }
}
