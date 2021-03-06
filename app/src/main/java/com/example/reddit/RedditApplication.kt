package com.example.reddit

import android.app.Application
import com.example.reddit.di.AppComponent
import com.example.reddit.di.DaggerAppComponent
import com.example.reddit.di.module.AppModule

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