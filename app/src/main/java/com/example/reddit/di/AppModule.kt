package com.example.reddit.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class AppModule constructor(private val application: Application) {

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application
    }

}
