package com.feature.main

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.customview.HorizontalItemDecorator
import com.domain.entity.DataWrapper
import com.domain.entity.Post
import com.feature.main.di.MainComponent
import com.feature.main.di.MainComponentFactoryProvider
import com.feature.main.di.MainModule
import com.reddit.feature.R
import com.reddit.feature.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var presenter: MainContract.Presenter

    private val adapter = PostAdapter(::onItemClicked, ::onLastPostReached)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMainComponent().inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)

        binding.postsRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.postsRv.addItemDecoration(HorizontalItemDecorator(resources.getDimensionPixelSize(R.dimen.padding_half)))
        binding.postsRv.adapter = adapter

        presenter.getTopPosts(null)
    }

    override fun onDestroy() {
        presenter.onDestroy()

        super.onDestroy()
    }

    private fun onItemClicked(url: String) {
        val params = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(resources.getColor(R.color.colorPrimary, null))
            .build()
        CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(params)
            .build()
            .launchUrl(this, Uri.parse(url))
    }

    private fun onLastPostReached(postId: String?) {
        presenter.getTopPosts(postId)
    }

    override fun renderPosts(posts: List<DataWrapper<Post>>) {
        adapter.addPosts(posts)
    }

    override fun renderError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getMainComponent(): MainComponent =
        (application as MainComponentFactoryProvider).provideMainComponentFactory()
            .create(module = MainModule(this))

}
