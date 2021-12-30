package com.reddit.di.module

import com.data.MainRepositoryImpl
import com.data.RedditRetrofitService
import com.domain.repository.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun bindMainRepository(redditRetrofit: RedditRetrofitService): MainRepository {
        return MainRepositoryImpl(redditRetrofit)
    }
}