plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.gradlePlugin.kotlin)
    implementation(libs.android.tools.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "theblackbit.animemania.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }

        register("androidModule") {
            id = "theblackbit.animemania.android.module"
            implementationClass = "AndroidModulePlugin"
        }

        register("androidFeature") {
            id = "theblackbit.animemania.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }

        register("androidRoom") {
            id = "theblackbit.animemania.android.room"
            implementationClass = "AndroidRoomPlugin"
        }
    }
}
