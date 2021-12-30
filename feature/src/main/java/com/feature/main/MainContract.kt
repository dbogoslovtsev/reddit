package com.feature.main

import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.core.base.BasePresenter
import com.core.base.BaseView

interface MainContract {

    interface View : BaseView {
        fun renderPosts(posts: List<DataWrapper<Post>>)
    }

    interface Presenter : BasePresenter {
        fun getTopPosts(after: String?)
    }

}