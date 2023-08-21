import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("theblackbit.animemania.android.module")
                apply("kotlin-kapt")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig.vectorDrawables.useSupportLibrary = true
                buildFeatures.dataBinding = true
                buildFeatures.viewBinding = true
            }
            uiDependencies()
            androidTestDependencies()
            rxDependencies()
            dependencies {
                "implementation"(project(":core:data"))
                "implementation"(project(":core:domain"))
                "implementation"(project(":core:util"))
                "implementation"(project(":core:model"))
            }
        }
    }
}