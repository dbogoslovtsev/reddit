package com.example.data.interceptor

import com.example.domain.keystore.KeyStore
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor constructor(val keyStore: KeyStore) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val headeredRequestBuilder = originalRequest.newBuilder()

        headeredRequestBuilder.addHeader("Content-Type", "application/json")
        headeredRequestBuilder.addHeader("Authorization", "bearer " + keyStore.provideToken())

        return chain.proceed(headeredRequestBuilder.build())
    }
}
