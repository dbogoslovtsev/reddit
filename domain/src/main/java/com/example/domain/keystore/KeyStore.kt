package com.example.domain.keystore

interface KeyStore {

    fun provideToken(): String

    fun saveToken(token: String)

}