package com.example.data

import com.example.data.response.BaseResponse
import com.example.domain.entity.TopPosts
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditRetrofitService {

    @GET("/top")
    fun getTopPosts(@Query("limit") limit: Int,
                    @Query("t") time: String,
                    @Query("after") after: String?): Single<BaseResponse<TopPosts>>
}