import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.rxDependencies() {
    dependencies {
        "implementation"(libsCatalog.findLibrary("rx.java.3").get())
    }
}