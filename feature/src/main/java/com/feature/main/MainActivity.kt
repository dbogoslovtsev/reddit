package com.feature.main

import android.net.Uri
import android.os.Bundle
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.base.BaseActivity
import com.core.customview.HorizontalItemDecorator
import com.feature.main.di.MainComponent
import com.feature.main.di.MainComponentFactoryProvider
import com.feature.main.di.MainModule
import com.reddit.feature.R
import com.reddit.feature.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainViewModel>() {

    private lateinit var binding: ActivityMainBinding

    private val adapter = PostAdapter(::onItemClicked, ::onLastPostReached)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMainComponent().inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            postsRv.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            postsRv.addItemDecoration(
                HorizontalItemDecorator(
                    resources.getDimensionPixelSize(
                        R.dimen.padding_half
                    )
                )
            )
            postsRv.adapter = adapter
        }

        with(viewModel) {
            topPostsLiveData.observe(this@MainActivity) {
                adapter.addPosts(it)
            }
        }
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
        viewModel.getTopPosts(postId)
    }

    private fun getMainComponent(): MainComponent =
        (application as MainComponentFactoryProvider).provideMainComponentFactory()
            .create(module = MainModule(this))

}
