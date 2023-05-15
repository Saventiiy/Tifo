plugins {
    id("convention.android-library")
    kotlin("kapt")
}

android {
    namespace = "com.github.tifo.domain"
}

dependencies {
    implementation(project(Module.Data.data))

    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    implementation(Dependencies.Coroutines.core)
}
