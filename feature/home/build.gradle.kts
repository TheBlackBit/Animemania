plugins {
    id("theblackbit.animemania.android.feature")
}

android {
    namespace = "com.theblackbit.animemania.android.feature.home"
}

dependencies {
    implementation(libs.paging.runtime)
    implementation(libs.paging.rxjava3)
    implementation(project(":feature:common"))
}
