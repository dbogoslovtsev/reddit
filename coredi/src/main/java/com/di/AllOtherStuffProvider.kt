package com.di

import com.domain.executor.UIExecutionThread
import com.domain.executor.WorkExecutionThread
import com.domain.repository.MainRepository
import com.domain.usecase.GetTopPostsUc

interface AllOtherStuffProvider {

    fun provideGetTopPostsUc(): GetTopPostsUc

//    fun provideWorkExecutionThread(): WorkExecutionThread
//
//    fun provideUiExecutionThread(): UIExecutionThread
//
//    fun provideMainRepository(): MainRepository

}