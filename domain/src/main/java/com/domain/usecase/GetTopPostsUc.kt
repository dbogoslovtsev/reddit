package com.domain.usecase

import com.domain.repository.MainRepository
import javax.inject.Inject

class GetTopPostsUc @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun call(after: String?) = mainRepository.getTopPosts(after)
}