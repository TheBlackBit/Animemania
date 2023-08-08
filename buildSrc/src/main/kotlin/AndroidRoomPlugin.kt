
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KaptExtension
import java.io.File

class AndroidRoomPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("kotlin-kapt")
            }

            extensions.configure<KaptExtension> {
                arguments {
                    arg("room.schemaLocation", "$projectDir/schemas")
                }
            }


            dependencies {
                "implementation"(libsCatalog.findLibrary("room.ktx").get())
                "implementation"(libsCatalog.findLibrary("room.guava").get())
                "implementation"(libsCatalog.findLibrary("room.runtime").get())
                "implementation"(libsCatalog.findLibrary("room.rxjava3").get())
                "kapt"(libsCatalog.findLibrary("room.compiler").get())
                "androidTestImplementation"(libsCatalog.findLibrary("room.testing").get())
            }
        }
    }

}
