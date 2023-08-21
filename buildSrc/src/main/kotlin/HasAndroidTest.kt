import org.gradle.api.Project

internal fun Project.hasAndroidTest(): Boolean {
    return projectDir.resolve("src/androidTest").exists()
}