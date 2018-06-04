package com.example.data.interceptor

import android.util.Base64
import com.example.data.RetrofitConfig
import okhttp3.Interceptor
import okhttp3.Response

class OauthHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val clientId64 = Base64.encodeToString(RetrofitConfig.CLIENT_ID.toByteArray(), Base64.NO_WRAP)
        val credentials = "Basic $clientId64"

        val headeredRequestBuilder = originalRequest.newBuilder()

        headeredRequestBuilder.addHeader("Content-Type", "application/x-www-form-urlencoded")
        headeredRequestBuilder.addHeader("Authorization", credentials)

        return chain.proceed(headeredRequestBuilder.build())

    }
}