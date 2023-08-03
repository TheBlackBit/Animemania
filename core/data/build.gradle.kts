plugins {
    id("theblackbit.animemania.android.module")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
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
}
