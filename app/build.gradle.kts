plugins {
    id("theblackbit.animemania.android.application")
}

android {
    namespace = "com.theblackbit.animemania.android"

    defaultConfig {
        applicationId = "com.theblackbit.animemania.android"
        versionCode = 2
        versionName = "1.0.0-Beta"
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
