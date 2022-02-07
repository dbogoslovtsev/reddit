package com.data

import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.domain.repository.RedditRepository
import javax.inject.Inject

class RedditRepositoryImpl @Inject constructor(private val retrofit: RedditRetrofitService) :
    RedditRepository {

    override suspend fun getTopPosts(after: String?): List<DataWrapper<Post>> {
        return retrofit.getTopPosts(limit = 10, time = "day", after = after)
            .data?.posts!! // TODO:  
    }

}