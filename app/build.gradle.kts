plugins {
    id("convention.android-application")
    id("convention.compose")
    kotlin("kapt")
}

android {
    namespace = Config.packageName

    defaultConfig {
        applicationId = Config.packageName
        versionCode = Config.versionCode
        versionName = Config.versionName
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(project(Module.Data.data))
    implementation(project(Module.Domain.domain))

    implementation(Dependencies.Android.material)
    implementation(Dependencies.Android.constraint)
    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.lifecycleViewmodelKtx)
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.fragmentKtx)

    implementation(Dependencies.Navigation.navigationCompose)

    implementation(Dependencies.Lifecycle.viewModelCompose)
    implementation(Dependencies.Lifecycle.runtimeCompose)

    implementation(Dependencies.Lottie.core)

    implementation(Dependencies.Dagger.dagger)
    kapt(Dependencies.Dagger.daggerCompiler)

    implementation(Dependencies.OkHttp3.loggingInterceptor)

    api(Dependencies.Retrofit.retrofit)
    implementation(Dependencies.Retrofit.gsonConverter)

    implementation(Dependencies.Room.roomRuntime)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.roomCompiler)

    implementation(Dependencies.Coil.core)
}