package com.example.data.keystore

import android.content.SharedPreferences
import com.example.domain.keystore.KeyStore

private const val TOKEN_PREF = "tokenPref"

class KeyStoreImpl(private val sharedPreferences: SharedPreferences) : KeyStore {

    override fun provideToken(): String {
        return sharedPreferences.getString(TOKEN_PREF, "") ?: ""
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_PREF, token).commit()
    }

}