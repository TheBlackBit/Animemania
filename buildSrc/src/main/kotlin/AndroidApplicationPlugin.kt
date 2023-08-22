import SingInConfigs.RELEASE
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import java.io.File
import java.io.FileInputStream
import java.util.Properties

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
                configureSignInConfigs(target)
                configureKotlinAndroid(this)
                configureBuildTypes()
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

    private fun ApplicationExtension.configureBuildTypes() {
        buildTypes {
            getByName("release") {
                isDebuggable = false
                isMinifyEnabled = true
                isShrinkResources = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
                this.signingConfig = signingConfigs.getByName(RELEASE)
            }

            getByName("debug") {
                isDebuggable = true
                enableUnitTestCoverage = true
                enableAndroidTestCoverage = true
            }
        }
    }

    private fun ApplicationExtension.configureSignInConfigs(project: Project) {

        val propertiesFile = project.rootProject.file("secrets.defaults.properties")
        val keyStoreFile = project.rootProject.file("${project.projectDir}/keystore.jks")

        val properties = Properties()

        properties.load(FileInputStream(propertiesFile))


        signingConfigs {
            signingConfigs.create(RELEASE) {
                storeFile = keyStoreFile
                storePassword = properties.getProperty("KEY_PASS")
                keyAlias = properties.getProperty("ALIAS")
                keyPassword = properties.getProperty("KEY_ALIAS")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }
}
