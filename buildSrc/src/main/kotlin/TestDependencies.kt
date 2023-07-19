import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.testDependencies() {

    dependencies {
        "implementation"(libsCatalog.findLibrary("junit.4").get())
    }
}