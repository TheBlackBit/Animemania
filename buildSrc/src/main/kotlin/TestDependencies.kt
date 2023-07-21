import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.testDependencies() {

    dependencies {
        "testImplementation"(libsCatalog.findLibrary("junit.4").get())
        "testImplementation"(libsCatalog.findLibrary("paging-common").get())
        "testImplementation"(libsCatalog.findLibrary("mockito.core").get())
        "testImplementation"(libsCatalog.findLibrary("kotlinx-coroutines-test").get())

    }
}