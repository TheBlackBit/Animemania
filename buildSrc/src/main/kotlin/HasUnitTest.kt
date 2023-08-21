import org.gradle.api.Project

internal fun Project.hasUnitTest(): Boolean {
    return projectDir.resolve("src/test").exists()
}