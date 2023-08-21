plugins {
    id("theblackbit.animemania.android.module")
}

android {
    namespace = "com.theblackbit.animemania.android.core.resources"
}

dependencies {
    implementation(libs.app.compat)
    implementation(libs.material.material)
}
