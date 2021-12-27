package com.reddit.di.module

import android.content.Context
import android.preference.PreferenceManager
import com.data.keystore.KeyStoreImpl
import com.domain.keystore.KeyStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class KeyStoreModule {

    @Provides
    @Singleton
    fun provideMainRepository(appContext: Context): KeyStore {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(appContext)
        return KeyStoreImpl(sharedPreferences)
    }
}