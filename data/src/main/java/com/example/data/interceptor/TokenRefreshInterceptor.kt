package com.example.data.interceptor

import com.example.data.OauthRetrofitService
import com.example.data.response.OauthResponse
import com.example.domain.keystore.KeyStore
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Call

class TokenRefreshInterceptor constructor(val oauthRetrofit: OauthRetrofitService,
                                          val keyStore: KeyStore) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var response = chain.proceed(request)

        if (response.code() == 403) {


            val requestBody = mapOf(
                    "https://oauth.reddit.com/grants/installed_client" to "grant_type",
                    "DO_NOT_TRACK_THIS_DEVICE" to "device_id")
            val call: Call<OauthResponse> = oauthRetrofit
                    .getToken(requestBody)

            try {
                val oauthResponse = call.execute()
                oauthResponse?.body()?.apply {
                    keyStore.saveToken(token)

                    val newHeaders = request.headers().newBuilder()
                            .add("Content-Type", "application/json")
                            .add("Authorization", "bearer $token")
                            .build()

                    val builder = request.newBuilder().headers(newHeaders)
                    val retryRequest = builder.build()

                    response = chain.proceed(retryRequest)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return response
    }

}