// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    @Suppress("DSL_SCOPE_VIOLATION")
    id(libs.plugins.android.application.get().pluginId) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    id(libs.plugins.android.library.get().pluginId) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    id(libs.plugins.kotlin.android.get().pluginId) apply false
    @Suppress("DSL_SCOPE_VIOLATION")
    id(libs.plugins.secrets.get().pluginId) version("2.0.1") apply false
}
