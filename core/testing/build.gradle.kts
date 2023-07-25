plugins {
    id("theblackbit.animemania.android.module")
}

android {
    namespace = "com.theblackbit.animemania.android.core.testing"
}

dependencies {
    api(libs.testing.core)
    api(libs.testing.runner)
    api(libs.testing.rules)
}