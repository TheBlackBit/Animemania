tasks.register("installGitHook", Copy::class) {
    val suffix =
        if (org.apache.tools.ant.taskdefs.condition.Os.isFamily(org.apache.tools.ant.taskdefs.condition.Os.FAMILY_WINDOWS)) {
            "windows"
        } else {
            "macos"
        }

    from(File(rootProject.rootDir, "scripts/pre-commit-$suffix"))
    into(File(rootProject.rootDir, ".git/hooks"))
    rename("pre-commit-$suffix", "pre-commit")

    from(File(rootProject.rootDir, "scripts/pre-push-$suffix"))
    into(File(rootProject.rootDir, ".git/hooks"))
    rename("pre-push-$suffix", "pre-push")

    eachFile {
        fileMode = 775
    }
}

tasks.getByPath(":app:preBuild").dependsOn(tasks.getByName("installGitHook"))