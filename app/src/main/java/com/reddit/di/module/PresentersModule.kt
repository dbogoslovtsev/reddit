package com.reddit.di.module

import com.reddit.presentation.main.MainContract
import com.reddit.presentation.main.MainPresenter
import dagger.Binds
import dagger.Module

@Module
abstract class PresentersModule {

    @Binds
    abstract fun bindMainPresenter(presenter: MainPresenter): MainContract.Presenter

}