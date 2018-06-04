package com.example.data

import com.example.data.response.OauthResponse
import retrofit2.Call
import retrofit2.http.*

interface OauthRetrofitService {

    @FormUrlEncoded
    @POST("/api/v1/access_token")
    fun getToken(@FieldMap body: Map<String, String>): Call<OauthResponse>

}