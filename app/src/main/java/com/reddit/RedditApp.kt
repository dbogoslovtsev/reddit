package com.reddit

import android.app.Application
import com.feature.di.FeatureComponent
import com.feature.di.FeatureComponentBuilderProvider
import com.reddit.di.AppComponent
import com.reddit.di.AppModule
import com.reddit.di.DaggerAppComponent

class RedditApp : Application(), FeatureComponentBuilderProvider {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.factory().create(AppModule(this))
    }

    override fun provideFeatureComponentBuilder(): FeatureComponent.Builder {
        return appComponent.featureComponentBuilder()
    }

}