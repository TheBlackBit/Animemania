import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.coreDependencies() {

    dependencies {
        "implementation"(libsCatalog.findLibrary("androidx.ktx").get())
        "implementation"(libsCatalog.findLibrary("koin.core").get())
        "implementation"(libsCatalog.findLibrary("koin.android").get())
    }
}