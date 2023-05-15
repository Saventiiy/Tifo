package com.github.tifo.di.module

import com.github.tifo.data.source.remote.api.GitHubApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
internal open class ApiModule {

    @Reusable
    @Provides
    open fun provideGitHubApiService(retrofit: Retrofit): GitHubApi =
        retrofit.create(GitHubApi::class.java)

}