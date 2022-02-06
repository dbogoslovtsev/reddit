package com.reddit

import android.app.Application
import com.feature.di.MainComponent
import com.feature.di.MainComponentFactoryProvider
import com.reddit.di.AppComponent
import com.reddit.di.AppModule
import com.reddit.di.DaggerAppComponent

class RedditApp : Application(), MainComponentFactoryProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.factory().create(AppModule(this))
    }

    override fun provideMainComponentFactory(): MainComponent.Factory =
        appComponent.mainComponentFactory()

}