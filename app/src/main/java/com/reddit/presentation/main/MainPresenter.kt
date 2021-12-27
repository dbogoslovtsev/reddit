package com.reddit.presentation.main

import com.example.domain.entity.Post
import com.example.domain.entity.DataWrapper
import com.example.domain.usecase.GetTopPostsUc
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainPresenter @Inject constructor(private val getTopPostsUc: GetTopPostsUc) :
    MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun onCreate(view: MainContract.View) {
        this.view = view
    }

    override fun getTopPosts(after: String?) {
        getTopPostsUc.execute(object : DisposableSingleObserver<List<DataWrapper<Post>>>() {
            override fun onSuccess(posts: List<DataWrapper<Post>>) {
                view?.renderPosts(posts)
            }

            override fun onError(e: Throwable) {
                view?.renderError(e.localizedMessage)
            }
        }, GetTopPostsUc.Params(after))
    }

    override fun onDestroy() {
        view = null
        getTopPostsUc.dispose()
    }
}