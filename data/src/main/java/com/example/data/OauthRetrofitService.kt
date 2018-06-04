package com.example.data

import com.example.data.response.OauthResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OauthRetrofitService {

    @POST("/api/v1/access_token")
    fun getToken(@Body body: Map<String, Any>): Call<OauthResponse>

}