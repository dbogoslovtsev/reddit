package com.feature.main

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.di.ApplicationProvider
import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.presentation.customview.HorizontalItemDecorator
import com.reddit.feature.R
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val appComponent get() = (application as ApplicationProvider).getApplicationComponent()

    @Inject
    @JvmField
    var presenter: MainContract.Presenter? = null

    private val adapter = PostAdapter(::onItemClicked, ::onLastPostReached)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainActivityComponent.factory().create(appComponent).inject(this)

        postsRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsRv.addItemDecoration(HorizontalItemDecorator(resources.getDimensionPixelSize(R.dimen.padding_half)))
        postsRv.adapter = adapter


        presenter?.onCreate(this)
        presenter?.getTopPosts(null)
    }

    override fun onDestroy() {
        presenter?.onDestroy()

        super.onDestroy()
    }

    private fun onItemClicked(url: String) {
        CustomTabsIntent.Builder()
                .setToolbarColor(resources.getColor(R.color.colorPrimary))
                .build()
                .launchUrl(this, Uri.parse(url))
    }

    private fun onLastPostReached(postId: String?) {
        presenter?.getTopPosts(postId)
    }

    override fun renderPosts(posts: List<DataWrapper<Post>>) {
        adapter.addPosts(posts)
    }

    override fun renderError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
