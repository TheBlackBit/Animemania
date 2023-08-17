plugins {
    id("theblackbit.animemania.android.application")
}

android {
    namespace = "com.theblackbit.animemania.android"

    defaultConfig {
        applicationId = "com.theblackbit.animemania.android"
        versionCode = 1
        versionName = "1.0"
    }
    @Suppress("UnstableApiUsage")
    buildTypes {
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            applicationIdSuffix = ".dev"
            isDebuggable = true
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
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
