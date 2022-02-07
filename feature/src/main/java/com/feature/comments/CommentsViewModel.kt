package com.feature.comments

import androidx.lifecycle.SavedStateHandle
import com.core.base.BaseViewModel
import com.domain.repository.RedditRepository
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val redditRepository: RedditRepository
) : BaseViewModel() {

    override fun init(savedStateHandle: SavedStateHandle) {

    }
}