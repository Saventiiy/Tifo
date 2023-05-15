package com.github.tifo

import android.app.Activity
import android.app.Application
import com.github.tifo.di.components.AppComponent
import com.github.tifo.di.components.DaggerAppComponent

internal open class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().build(this)
    }
}

internal val Activity.component: AppComponent get() = (application as App).component