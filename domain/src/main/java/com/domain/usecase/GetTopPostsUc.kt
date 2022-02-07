package com.domain.usecase

import com.domain.repository.RedditRepository
import javax.inject.Inject

class GetTopPostsUc @Inject constructor(
    private val redditRepository: RedditRepository
) {
    suspend fun call(after: String?) = redditRepository.getTopPosts(after)
}