import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.internal.tasks.JacocoTask
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.plugins.JacocoTaskExtension
import org.gradle.testing.jacoco.tasks.JacocoCoverageVerification
import org.gradle.testing.jacoco.tasks.JacocoReport
import java.util.Locale

private val coverageExclusions = listOf(
    // Android
    "**/R.class",
    "**/R\$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    // data binding
    "androidx/databinding/**/*.class",
    "**/androidx/databinding/*Binding.class",
    "**/*MapperImpl*.*",
    "**/**Bind**/**"
)

private fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
}

internal fun Project.configureJacoco() {
    extensions.getByType<JacocoPluginExtension>().run {
        toolVersion = "0.8.8"
    }

    tasks.create("jacocoMergedReport", JacocoReport::class.java) {
        dependsOn("testDebugUnitTest")

       /* Android Test code coverage disable
        if(hasAndroidTest()) {
            dependsOn("createDebugCoverageReport")
        }*/

        reports {
            xml.required.set(true)
            html.required.set(true)
        }

        val kotlinTree = fileTree("$buildDir/tmp/kotlin-classes/debug") {
            coverageExclusions
        }

        classDirectories.from(kotlinTree)

        sourceDirectories.setFrom(files("$projectDir/src/main/java"))

        executionData.setFrom(
            fileTree(project.buildDir) {
                include(
                //  Disable report for android test coverage   "outputs/code_coverage/**/*.ec",
                    "outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec"
                )
            }
        )
    }

    tasks.withType<Test>().run {
        all {
            configure<JacocoTaskExtension>() {
                isIncludeNoLocationClasses = true
                excludes = listOf("jdk.internal.*")
            }
        }
    }
}
