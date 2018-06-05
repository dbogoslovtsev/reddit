package com.example.reddit.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.domain.entity.DataWrapper
import com.example.domain.entity.Post
import com.example.reddit.R
import com.example.reddit.RedditApplication
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject
    @JvmField
    var presenter: MainContract.Presenter? = null

    val adapter = PostAdapter(::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsRv.adapter = adapter

        RedditApplication.appComponent.inject(this)
        presenter?.onCreate(this)
        presenter?.getTopPosts(null)
    }

    override fun onDestroy() {
        presenter?.onDestroy()

        super.onDestroy()
    }


    private fun onItemClicked(url: String) {
        renderError(url)
    }

    override fun renderPosts(posts: List<DataWrapper<Post>>) {
        adapter.addPosts(posts)
    }

    override fun renderError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
