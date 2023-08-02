plugins {
    id("theblackbit.animemania.android.feature")
}

android {
    namespace = "com.theblackbit.animemania.android.feature.detail"
}

dependencies {
    implementation(libs.paging.runtime)
    implementation(libs.paging.rxjava3)
    implementation(libs.arcView)
    implementation(project(":feature:common"))
}
