plugins {
    id("theblackbit.animemania.android.application")
}

apply(from = "../git-hook.gradle.kts")

android {
    namespace = "com.theblackbit.animemania.android"

    defaultConfig {
        applicationId = "com.theblackbit.animemania.android"
        versionCode = 1
        versionName = "0.0.0"

    }
}

dependencies {
    implementation(project(":core:util"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:testing"))
    implementation(project(":feature:home"))
    implementation(project(":feature:detail"))
}
