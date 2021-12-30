package com.presentation.base

interface BasePresenter<View> {

    fun onCreate(view: View)

    fun onDestroy()

}

