package com.domain.keystore

interface KeyStore {

    fun provideToken(): String

    fun saveToken(token: String)

}