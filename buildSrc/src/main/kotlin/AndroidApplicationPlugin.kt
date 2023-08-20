import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.LibraryAndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jlleitschuh.gradle.ktlint")
                apply("org.gradle.jacoco")
                apply("kotlin-kapt")
            }
            extensions.configure<ApplicationExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = Versions.targetSdk
                defaultConfig.vectorDrawables.useSupportLibrary = true

                buildFeatures.dataBinding = true
                buildFeatures.viewBinding = true
            }

            configureJacoco()


            rxDependencies()
            coreDependencies()
            uiDependencies()
            testDependencies()
            androidTestDependencies()
        }
    }
}
