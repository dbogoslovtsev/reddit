package com.domain.repository

import com.domain.entity.Post
import com.domain.entity.DataWrapper

interface RedditRepository {
    suspend fun getTopPosts(after: String?): List<DataWrapper<Post>>
}