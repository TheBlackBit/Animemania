import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.uiDependencies() {

    dependencies {
        "implementation"(libsCatalog.findLibrary("material.material").get())
        "implementation"(libsCatalog.findLibrary("androidx.lifecycle.runtime").get())
        "implementation"(libsCatalog.findLibrary("app.compat").get())
        "implementation"(project(":resources"))
    }
}