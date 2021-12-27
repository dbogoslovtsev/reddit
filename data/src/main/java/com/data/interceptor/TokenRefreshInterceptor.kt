package com.data.interceptor

import com.data.OauthRetrofitService
import com.data.response.OauthResponse
import com.domain.keystore.KeyStore
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Call

class TokenRefreshInterceptor constructor(
    private val oauthRetrofit: OauthRetrofitService,
    private val keyStore: KeyStore
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        var response = chain.proceed(request)

        if (response.code == 401) {

            val requestBody = mapOf(
                "grant_type" to "https://oauth.reddit.com/grants/installed_client",
                "device_id" to "DO_NOT_TRACK_THIS_DEVICE"
            )
            val call: Call<OauthResponse> = oauthRetrofit
                .getToken(requestBody)

            try {
                val oauthResponse = call.execute()
                oauthResponse.body()?.apply {
                    keyStore.saveToken(token)

                    val newHeaders = request.headers.newBuilder()
                        .set("Content-Type", "application/json")
                        .set("Authorization", "bearer $token")
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