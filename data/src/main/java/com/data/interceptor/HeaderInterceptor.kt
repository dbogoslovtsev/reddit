package com.data.interceptor

import com.domain.keystore.KeyStore
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor constructor(private val keyStore: KeyStore) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val headeredRequestBuilder = originalRequest.newBuilder()

        headeredRequestBuilder.addHeader("Content-Type", "application/json")
        if (keyStore.provideToken().isNotEmpty())
            headeredRequestBuilder.addHeader("Authorization", "bearer ${keyStore.provideToken()}")
        else headeredRequestBuilder.addHeader("Authorization", "bearer 1234567890")

        return chain.proceed(headeredRequestBuilder.build())
    }
}
