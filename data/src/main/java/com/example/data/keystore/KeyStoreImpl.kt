package com.example.data.keystore

import android.content.SharedPreferences
import com.example.domain.keystore.KeyStore

class KeyStoreImpl(val sharedPreferences: SharedPreferences) : KeyStore {

    private final val TOKEN_PREF = "tokenPref"

    override fun provideToken(): String {
        return sharedPreferences.getString(TOKEN_PREF, "")
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_PREF, token).commit()
    }
}