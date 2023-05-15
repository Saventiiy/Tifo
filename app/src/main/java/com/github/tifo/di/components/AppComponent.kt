package com.github.tifo.di.components

import android.app.Application
import com.github.tifo.di.module.ApiModule
import com.github.tifo.di.module.DatabaseModule
import com.github.tifo.di.module.HttpModule
import com.github.tifo.screens.detailed.DetailedViewModel
import com.github.tifo.screens.search.SearchViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ApiModule::class,
        HttpModule::class,
        DatabaseModule::class]
)
internal interface AppComponent {

    @Component.Factory
    interface Factory {
        fun build(@BindsInstance application: Application): AppComponent
    }

    val searchViewModel : SearchViewModel
    val detailedViewModel : DetailedViewModel.Factory
}
