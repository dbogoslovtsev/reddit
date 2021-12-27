package com.reddit.presentation.base

interface BasePresenter<View> {

    fun onCreate(view: View)

    fun onDestroy()

}

