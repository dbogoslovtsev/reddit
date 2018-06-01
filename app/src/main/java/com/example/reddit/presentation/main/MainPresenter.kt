package com.example.reddit.presentation.main

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun onCreate(view: MainContract.View?) {
        this.view = view
    }

    override fun onDestroy() {
        view = null
    }
}