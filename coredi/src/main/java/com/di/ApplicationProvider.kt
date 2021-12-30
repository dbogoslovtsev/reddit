package com.di

import android.app.Application
import android.content.Context

interface ApplicationProvider {
    fun getApplicationComponent(): ApplicationComponentProvider
    fun getApplication(): Application
    fun getApplicationContext(): Context
}