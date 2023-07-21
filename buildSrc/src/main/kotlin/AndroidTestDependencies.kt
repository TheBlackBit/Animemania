import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.androidTestDependencies() {

    dependencies {
        "androidTestImplementation"(libsCatalog.findLibrary("androidtest.junit").get())
        "androidTestImplementation"(libsCatalog.findLibrary("espresso.core").get())
    }
}