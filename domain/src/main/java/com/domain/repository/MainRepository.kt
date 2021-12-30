package com.domain.repository

import com.domain.entity.Post
import com.domain.entity.DataWrapper
import io.reactivex.Single

interface MainRepository {
    fun getTopPosts(after: String?): Single<List<DataWrapper<Post>>>
}