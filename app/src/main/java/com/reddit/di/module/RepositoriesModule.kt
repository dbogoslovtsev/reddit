package com.reddit.di.module

import com.data.MainRepositoryImpl
import com.data.RedditRetrofitService
import com.domain.repository.MainRepository
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