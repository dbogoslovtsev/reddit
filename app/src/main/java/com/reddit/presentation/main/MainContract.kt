package com.reddit.presentation.main

import com.example.domain.entity.DataWrapper
import com.example.domain.entity.Post
import com.reddit.presentation.base.BasePresenter
import com.reddit.presentation.base.BaseView

interface MainContract {

    interface View : BaseView {
        fun renderPosts(posts: List<DataWrapper<Post>>)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopPosts(after: String?)
    }

}