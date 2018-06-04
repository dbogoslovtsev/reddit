package com.example.reddit.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.reddit.R
import com.example.reddit.RedditApplication
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    @JvmField
    var presenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RedditApplication.appComponent.inject(this)
        presenter?.onCreate(this)
        presenter?.getTopPosts(null)
    }

    override fun onDestroy() {
        presenter?.onDestroy()

        super.onDestroy()
    }

    override fun renderError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
