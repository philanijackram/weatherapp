// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.huawei.agconnect:agcp:1.5.2.300")
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://developer.huawei.com/repo/")
        }
    }
    dependencies {
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.safe.args) apply false
    alias(libs.plugins.hilt) apply false
}