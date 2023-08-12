plugins {
    id("theblackbit.animemania.android.module")
}

android {
    namespace = "com.theblackbit.animemania.android.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:util"))
    implementation(project(":core:data"))
    implementation(libs.paging.runtime)
    implementation(libs.paging.rxjava3)
}
