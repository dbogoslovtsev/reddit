package com.domain.usecase

import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.domain.executor.UIExecutionThread
import com.domain.executor.WorkExecutionThread
import com.domain.repository.MainRepository
import com.domain.usecase.base.AbsUseCaseSingle
import io.reactivex.Single
import javax.inject.Inject

class GetTopPostsUc @Inject constructor(
    threadExecutor: WorkExecutionThread,
    postExecutionThread: UIExecutionThread,
    private val mainRepository: MainRepository
) : AbsUseCaseSingle<List<DataWrapper<Post>>, GetTopPostsUc.Params>(
    threadExecutor,
    postExecutionThread
) {

    override fun buildUseCaseObservable(params: Params?): Single<List<DataWrapper<Post>>> {
        return mainRepository.getTopPosts(params?.after)
    }

    data class Params(val after: String?)

}