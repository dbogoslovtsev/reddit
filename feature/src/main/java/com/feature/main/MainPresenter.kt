package com.feature.main

import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.domain.usecase.GetTopPostsUc
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val view: MainContract.View,
    private val getTopPostsUc: GetTopPostsUc
) : MainContract.Presenter {

    override fun getTopPosts(after: String?) {
        getTopPostsUc.execute(object : DisposableSingleObserver<List<DataWrapper<Post>>>() {
            override fun onSuccess(posts: List<DataWrapper<Post>>) {
                view.renderPosts(posts)
            }

            override fun onError(e: Throwable) {
                view.renderError(e.localizedMessage)
            }
        }, GetTopPostsUc.Params(after))
    }

    override fun onDestroy() {
        getTopPostsUc.dispose()
    }
}