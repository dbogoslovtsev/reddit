package com.domain.di.module

import com.domain.repository.RedditRepository
import com.domain.usecase.GetTopPostsUc
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetTopPostsUc(redditRepository: RedditRepository) = GetTopPostsUc(redditRepository)

}