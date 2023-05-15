plugins {
    id("convention.android-library")
    kotlin("kapt")
}

android {
    namespace = "com.github.tifo.data"

    defaultConfig {
        buildConfigField("String", "DATABASE_NAME", '"' + Config.databaseName + '"')
        buildConfigField("int", "DATABASE_VERSION", Config.databaseVersion)
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    api(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)

    implementation(Dependencies.OkHttp3.loggingInterceptor)

    implementation(Dependencies.Coroutines.core)

    implementation(Dependencies.Pagingn.core)

    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

}
