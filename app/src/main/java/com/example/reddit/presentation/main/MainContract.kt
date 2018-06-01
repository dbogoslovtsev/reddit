package com.example.reddit.presentation.main

import com.example.reddit.presentation.base.BasePresenter
import com.example.reddit.presentation.base.BaseView

interface MainContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter.BasePresenter<View> {

    }

}