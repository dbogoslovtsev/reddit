package com.example.reddit.presentation.base

interface BasePresenter {

    interface BasePresenter<View> {

        fun onCreate(view: View?)

        fun onDestroy()

    }

}