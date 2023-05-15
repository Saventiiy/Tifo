import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    add("implementation", "androidx.compose.ui:ui:1.4.3")
    add("implementation", "androidx.compose.material:material:1.4.3")
    add("implementation", "androidx.compose.ui:ui-tooling:1.4.3")
    add("implementation", "androidx.compose.runtime:runtime:1.4.3")
}