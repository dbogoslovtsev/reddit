package com.domain.usecase

import com.domain.repository.MainRepository
import com.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetTopPostsUc @Inject constructor(
    private val mainRepository: MainRepository
) : BaseUseCase() {

    suspend fun execute(after: String?) = mainRepository.getTopPosts(after)

}