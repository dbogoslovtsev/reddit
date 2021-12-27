package com.reddit

import android.app.Application
import com.reddit.di.AppComponent
import com.reddit.di.DaggerAppComponent
import com.reddit.di.module.AppModule

class RedditApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}