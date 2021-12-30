package com.reddit

import android.app.Application
import android.content.Context
import com.di.ApplicationComponentProvider
import com.di.ApplicationProvider
import com.reddit.di.component.ApplicationComponent
import com.reddit.di.component.DaggerApplicationComponent

class RedditApplication : Application(), ApplicationProvider {

    companion object {
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    override fun getApplicationComponent(): ApplicationComponentProvider = appComponent

    override fun getApplication(): Application = this

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    private fun initAppComponent() {
        appComponent = DaggerApplicationComponent.builder().build()
    }

}