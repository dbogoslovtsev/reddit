package com.example.domain.usecase

import com.example.domain.entity.Post
import com.example.domain.entity.DataWrapper
import com.example.domain.repository.MainRepository
import com.example.domain.usecase.base.AbsUseCaseSingle
import com.shark.radio.usecase.executor.UIExecutionThread
import com.shark.radio.usecase.executor.WorkExecutionThread
import io.reactivex.Single
import javax.inject.Inject

class GetTopPostsUc @Inject constructor(threadExecutor: WorkExecutionThread,
                                        postExecutionThread: UIExecutionThread,
                                        val mainRepository: MainRepository)
    : AbsUseCaseSingle<List<DataWrapper<Post>>, GetTopPostsUc.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params?): Single<List<DataWrapper<Post>>> {
        return mainRepository.getTopPosts(params?.after)
    }

    data class Params(val after: String?)

}