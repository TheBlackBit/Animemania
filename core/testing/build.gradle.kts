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
    implementation(libs.paging.runtime)
    implementation(libs.espresso.core)
    implementation(libs.espresso.contrib)
    implementation(libs.espresso.intent)
    implementation(libs.espresso.web)
    implementation(libs.paging.runtime)
    implementation(libs.paging.rxjava3)
    implementation(libs.gson)
}
