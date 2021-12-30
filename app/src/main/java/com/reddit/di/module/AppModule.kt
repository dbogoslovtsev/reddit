package com.reddit.di.module

import android.app.Application
import android.content.Context
import com.di.ApplicationProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(appProvider: ApplicationProvider): Context {
        return appProvider.getApplicationContext()
    }

    @Singleton
    @Provides
    fun provideApplication(appProvider: ApplicationProvider): Application {
        return appProvider.getApplication()
    }

}
