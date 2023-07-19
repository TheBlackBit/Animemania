import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.coreDependencies() {

    dependencies {
        "implementation"(libsCatalog.findLibrary("androidx.ktx").get())
    }
}