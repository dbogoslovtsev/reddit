package com.data

import com.domain.entity.Post
import com.domain.entity.DataWrapper
import com.domain.repository.MainRepository
import io.reactivex.Single
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private var retrofit: RedditRetrofitService) :
    MainRepository {

    override fun getTopPosts(after: String?): Single<List<DataWrapper<Post>>> {
        return retrofit.getTopPosts(limit = 10, time = "day", after = after)
                .map { response -> response.data?.posts }
    }

}