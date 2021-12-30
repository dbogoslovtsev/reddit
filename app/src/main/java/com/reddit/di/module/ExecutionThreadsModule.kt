package com.reddit.di.module


import com.domain.executor.UIExecutionThread
import com.domain.executor.WorkExecutionThread
import com.reddit.di.executor.IOWorkThread
import com.reddit.di.executor.UIThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExecutionThreadsModule {

    @Provides
    @Singleton
    fun provideUiExecutionThread(): UIExecutionThread {
        return UIThread()
    }

    @Provides
    @Singleton
    fun provideWorkExecutionThread(): WorkExecutionThread {
        return IOWorkThread()
    }
}
