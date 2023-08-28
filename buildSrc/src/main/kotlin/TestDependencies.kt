import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.testDependencies() {
    dependencies {
        "testImplementation"(libsCatalog.findLibrary("junit4").get())
        "testImplementation"(libsCatalog.findLibrary("paging.common").get())
        "testImplementation"(libsCatalog.findLibrary("mockito.core").get())
        "testImplementation"(libsCatalog.findLibrary("kotlinx.coroutines.test").get())
        "testImplementation"(libsCatalog.findLibrary("mockito.inline").get())
        "testImplementation"(libsCatalog.findLibrary("koin.test").get())
        "testImplementation"(libsCatalog.findLibrary("koin.test.junit4").get())
    }
}
