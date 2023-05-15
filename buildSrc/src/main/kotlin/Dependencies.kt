object Dependencies {

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val material = "com.google.android.material:material:1.7.0"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1"
        const val activityCompose = "androidx.activity:activity-compose:1.6.1"
        const val activityKtx = "androidx.activity:activity-ktx:1.7.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.5.7"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val gsonConverter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Dagger {
        private const val version = "2.45"
        const val dagger = "com.google.dagger:dagger:$version"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
    }

    object Navigation {
        const val version = "2.5.3"
        const val navigationCompose = "androidx.navigation:navigation-compose:$version"
        const val navigationCommon = "androidx.navigation:navigation-common-ktx:$version"
    }

    object OkHttp3 {
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    }

    object Pagingn {
        const val core = "androidx.paging:paging-common-ktx:3.1.1"
    }

    object Coroutines {
        private const val version = "1.6.4"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
    }

    object Room {
        private const val room_version = "2.5.1"
        const val roomRuntime = "androidx.room:room-runtime:$room_version"
        const val roomCompiler = "androidx.room:room-compiler:$room_version"
        const val roomKtx = "androidx.room:room-ktx:$room_version"
    }

    object Coil {
        const val core = "io.coil-kt:coil-compose:2.2.2"
    }

    object Lottie {
        const val core = "com.airbnb.android:lottie-compose:5.2.0"
    }

    object Lifecycle {
        private const val version = "2.6.0"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
        const val runtimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:$version"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
        const val activityCompose = "androidx.activity:activity-compose:1.3.1"
    }
}