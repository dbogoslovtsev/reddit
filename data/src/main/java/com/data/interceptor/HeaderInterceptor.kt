package com.data.interceptor

import com.domain.keystore.KeyStore
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeaderInterceptor constructor(private val keyStore: KeyStore) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequestBuilder = originalRequest.newBuilder()

        newRequestBuilder.addHeader("Content-Type", "application/json")
        if (keyStore.provideToken().isNotEmpty()) {
            newRequestBuilder.addHeader("Authorization", "bearer ${keyStore.provideToken()}")
        }

        return chain.proceed(newRequestBuilder.build())
    }
}
