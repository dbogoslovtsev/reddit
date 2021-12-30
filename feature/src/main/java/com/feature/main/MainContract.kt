package com.feature.main

import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.presentation.base.BasePresenter
import com.presentation.base.BaseView

interface MainContract {

    interface View : BaseView {
        fun renderPosts(posts: List<DataWrapper<Post>>)
    }

    interface Presenter : BasePresenter<View> {
        fun getTopPosts(after: String?)
    }

}