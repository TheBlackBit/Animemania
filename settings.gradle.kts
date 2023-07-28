pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Animemania"

include(":app")
include(":core:data")
include(":core:domain")
include(":core:util")
include(":core:testing")
include(":feature:common")
include(":feature:home")
include(":feature:detail")
include(":feature:character")
include(":feature:search")
include(":core:resources")
include(":core:model")
