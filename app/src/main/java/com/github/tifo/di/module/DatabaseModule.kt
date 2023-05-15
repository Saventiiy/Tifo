package com.github.tifo.di.module

import android.app.Application
import androidx.room.Room
import com.github.tifo.data.BuildConfig
import com.github.tifo.data.source.local.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal open class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(
            app.applicationContext,
            Database::class.java,
            BuildConfig.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRepositoriesDao(database: Database) = database.repositoriesDao()

    @Provides
    fun provideBranchesDao(database: Database) = database.branchesDao()

    @Provides
    fun provideContributorsDao(database: Database) = database.contributorsDao()
}