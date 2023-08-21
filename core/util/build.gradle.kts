plugins {
    id("theblackbit.animemania.android.module")
}

android {
    namespace = "com.theblackbit.animemania.android.core.util"
}

dependencies {
    implementation(libs.rxjava3.retrofit.adapter)
    implementation(libs.rx.java.get3())
    implementation(libs.retrofit)
}
