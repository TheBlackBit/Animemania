plugins {
    id("theblackbit.animemania.android.module")
    id("theblackbit.animemania.android.room")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.theblackbit.animemania.android.core.data"
}

secrets {
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.rxjava3.retrofit.adapter)
    implementation(libs.rx.java.get3())
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging)
    implementation(libs.paging.runtime)
    implementation(libs.paging.rxjava3)
    testImplementation(libs.paging.common)
    androidTestImplementation(libs.core.testing)
    androidTestImplementation(libs.androidtest.junit)
    androidTestImplementation(project(":core:testing"))
}
