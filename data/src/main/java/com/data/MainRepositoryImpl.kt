package com.data

import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.domain.repository.MainRepository
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private var retrofit: RedditRetrofitService) :
    MainRepository {

    override suspend fun getTopPosts(after: String?): List<DataWrapper<Post>> {
        return retrofit.getTopPosts(limit = 10, time = "day", after = after)
            .data?.posts!! // TODO:  
    }

}