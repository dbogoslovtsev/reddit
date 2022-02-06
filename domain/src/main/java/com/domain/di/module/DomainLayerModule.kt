package com.domain.di.module

import com.domain.repository.MainRepository
import com.domain.usecase.GetTopPostsUc
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetTopPostsUc(mainRepository: MainRepository) = GetTopPostsUc(mainRepository)

}