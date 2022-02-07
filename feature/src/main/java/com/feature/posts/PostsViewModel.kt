package com.feature.posts

import androidx.lifecycle.SavedStateHandle
import com.core.base.BaseViewModel
import com.core.base.SingleLiveEvent
import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.domain.usecase.GetTopPostsUc
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val getTopPostsUc: GetTopPostsUc
) : BaseViewModel() {

    val topPostsLiveData = SingleLiveEvent<List<DataWrapper<Post>>>()

    override fun init(savedStateHandle: SavedStateHandle) {
        savedStateHandle.getLiveData<List<DataWrapper<Post>>?>("topPostsLiveData").value?.run {
            topPostsLiveData.value = this
        } ?: getTopPosts()
    }

    fun getTopPosts(after: String? = null) = execute(
        { getTopPostsUc.call(after) }
    ) {
        onComplete = {
            topPostsLiveData.value = it
        }
    }

}