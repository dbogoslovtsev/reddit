package com.domain.di.module

import com.domain.executor.UIExecutionThread
import com.domain.executor.WorkExecutionThread
import com.domain.repository.MainRepository
import com.domain.usecase.GetTopPostsUc
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetTopPostsUc(
        threadExecutor: WorkExecutionThread,
        postExecutionThread: UIExecutionThread,
        mainRepository: MainRepository
    ): GetTopPostsUc = GetTopPostsUc(threadExecutor, postExecutionThread, mainRepository)

}