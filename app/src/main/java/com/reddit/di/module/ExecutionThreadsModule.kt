package com.reddit.di.module


import com.reddit.di.executor.IOWorkThread
import com.reddit.di.executor.UIThread
import com.shark.radio.usecase.executor.UIExecutionThread
import com.shark.radio.usecase.executor.WorkExecutionThread
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class ExecutionThreadsModule {

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
