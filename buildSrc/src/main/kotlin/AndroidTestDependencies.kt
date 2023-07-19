import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.androidTestDependencies() {

    dependencies {
        "implementation"(libsCatalog.findLibrary("androidtest.junit").get())
        "implementation"(libsCatalog.findLibrary("espresso.core").get())
    }
}