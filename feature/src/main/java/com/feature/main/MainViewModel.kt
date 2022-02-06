package com.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.core.base.BaseViewModel
import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.domain.usecase.GetTopPostsUc
import io.reactivex.observers.DisposableSingleObserver
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getTopPostsUc: GetTopPostsUc
) : BaseViewModel() {

    val topPostsLiveData = MutableLiveData<List<DataWrapper<Post>>>()


    override fun init(savedStateHandle: SavedStateHandle) {
        savedStateHandle.getLiveData<List<DataWrapper<Post>>?>("topPostsLiveData").value?.run {
            topPostsLiveData.value = this
        } ?: getTopPosts()
    }

    fun getTopPosts(after: String? = null) {
        viewModelScope.launch {
            getTopPostsUc.execute(object : DisposableSingleObserver<List<DataWrapper<Post>>>() {
                override fun onSuccess(posts: List<DataWrapper<Post>>) {
                    topPostsLiveData.value = posts
                }

                override fun onError(e: Throwable) {
                    errorLiveData.value = e.localizedMessage!!
                }
            }, GetTopPostsUc.Params(after))
        }
    }

}