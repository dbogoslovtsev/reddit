package com.reddit.di.module

import com.example.data.MainRepositoryImpl
import com.example.data.RedditRetrofitService
import com.example.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoriesModule {

    @Provides
    @Singleton
    fun provideMainRepository(redditRetrofit: RedditRetrofitService): MainRepository {
        return MainRepositoryImpl(redditRetrofit)
    }
}