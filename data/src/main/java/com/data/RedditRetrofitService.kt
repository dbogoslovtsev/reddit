package com.data

import com.data.response.BaseResponse
import com.domain.entity.TopPosts
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditRetrofitService {


    // TODO:
    //t
    //one of (hour, day, week, month, year, all)
    //
    //after
    //fullname of a thing
    //
    //before
    //fullname of a thing
    //
    //count
    //a positive integer (default: 0)
    //
    //limit
    //the maximum number of items desired (default: 25, maximum: 100)
    //
    //show
    //(optional) the string all
    //
    //sr_detail
    //(optional) expand subreddits
    @GET("/top")
    suspend fun getTopPosts(
        @Query("limit") limit: Int,
        @Query("t") time: String = "hour",
        @Query("after") after: String?
    ): BaseResponse<TopPosts>


}