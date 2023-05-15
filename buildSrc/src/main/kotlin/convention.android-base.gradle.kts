import com.android.build.gradle.BaseExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin

plugins {
    kotlin("android")
}

configure<BaseExtension> {
    compileSdkVersion(33)

    buildFeatures.viewBinding = true

    defaultConfig {
        minSdk = 29
        targetSdk = 33
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    lintOptions {
        isAbortOnError = false
        disable("UseCompoundDrawables")
    }
}
