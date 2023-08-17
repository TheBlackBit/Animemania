import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType


class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-kapt")
                apply("org.jlleitschuh.gradle.ktlint")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Versions.targetSdk
                defaultConfig.vectorDrawables.useSupportLibrary = true

                buildFeatures.dataBinding = true
                buildFeatures.viewBinding = true
            }

            rxDependencies()
            coreDependencies()
            uiDependencies()
            testDependencies()
            androidTestDependencies()
        }
    }
}
