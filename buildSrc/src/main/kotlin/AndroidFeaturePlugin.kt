import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("theblackbit.animemania.android.module")
            }
            extensions.configure<LibraryExtension> {
                defaultConfig.vectorDrawables.useSupportLibrary = true
                buildFeatures.dataBinding = true
                buildFeatures.viewBinding = true

            }
            uiDependencies()
            androidTestDependencies()
        }
    }
}