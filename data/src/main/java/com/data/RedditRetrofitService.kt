package com.data

import com.data.response.BaseResponse
import com.domain.entity.TopPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditRetrofitService {

    @GET("/top")
    suspend fun getTopPosts(
        @Query("limit") limit: Int,
        @Query("t") time: String,
        @Query("after") after: String?
    ): BaseResponse<TopPosts>
}