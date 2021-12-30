package com.reddit

import android.app.Application
import com.feature.main.di.MainComponent
import com.feature.main.di.MainComponentFactoryProvider
import com.reddit.di.AppComponent
import com.reddit.di.AppModule

class RedditApp : Application(), MainComponentFactoryProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
//        appComponent = DaggerAppComponent.factory().create(AppModule(this))
    }

    override fun provideMainComponentFactory(): MainComponent.Factory =
        appComponent.mainComponentFactory()

}