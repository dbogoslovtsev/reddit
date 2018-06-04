package com.example.domain.repository

import com.example.domain.entity.Post
import com.example.domain.entity.DataWrapper
import io.reactivex.Single

interface MainRepository {

    fun getTopPosts(after: String?): Single<List<DataWrapper<Post>>>
}