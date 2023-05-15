import com.android.build.gradle.AppExtension
import org.gradle.kotlin.dsl.configure

plugins {
    id("com.android.application")
    id("convention.android-base")
    id("convention.kotlin-base")
}

configure<AppExtension> {
    buildTypes {
        getByName("debug") {
            setMatchingFallbacks("release")
        }
    }
}