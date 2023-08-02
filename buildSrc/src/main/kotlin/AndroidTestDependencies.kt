import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.androidTestDependencies() {
    dependencies {
        "androidTestImplementation"(libsCatalog.findLibrary("androidtest.junit").get())
        "androidTestImplementation"(libsCatalog.findLibrary("espresso.core").get())
        "androidTestImplementation"(libsCatalog.findLibrary("espresso.contrib").get())
        "androidTestImplementation"(libsCatalog.findLibrary("espresso.intent").get())
        "androidTestImplementation"(libsCatalog.findLibrary("espresso.intent").get())
        "androidTestImplementation"(libsCatalog.findLibrary("espresso.web").get())
        "androidTestImplementation"(libsCatalog.findLibrary("koin.android.test").get())
        "androidTestImplementation"(libsCatalog.findLibrary("fragment.testing").get())
        "androidTestImplementation"(libsCatalog.findLibrary("navigation.testing").get())
        "androidTestImplementation"(project(":core:testing"))
        "kaptAndroidTest"(libsCatalog.findLibrary("databinding.compiler").get())
    }
}
