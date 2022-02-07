package com.feature.posts

import android.content.Intent
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.base.BaseActivity
import com.core.customview.HorizontalItemDecorator
import com.feature.comments.CommentsActivity
import com.feature.di.FeatureComponentBuilderProvider
import com.feature.di.PostsModule
import com.reddit.feature.R
import com.reddit.feature.databinding.ActivityPostsBinding

class PostsActivity : BaseActivity<PostsViewModel>() {

    private lateinit var binding: ActivityPostsBinding

    private val adapter = PostAdapter(::onItemClicked, ::onLastPostReached)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // TODO: Move to View layer
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            rvPosts.layoutManager =
                LinearLayoutManager(this@PostsActivity, LinearLayoutManager.VERTICAL, false)
            rvPosts.addItemDecoration(
                HorizontalItemDecorator(
                    resources.getDimensionPixelSize(
                        R.dimen.padding_half
                    )
                )
            )
            rvPosts.adapter = adapter
        }

        with(viewModel) {
            topPostsLiveData.observe(this@PostsActivity) {
                adapter.addPosts(it)
            }
        }
    }

    private fun onItemClicked(url: String) {
        startActivity(Intent(this, CommentsActivity::class.java))

//        val params = CustomTabColorSchemeParams.Builder()
//            .setToolbarColor(resources.getColor(R.color.colorPrimary, null))
//            .build()
//        CustomTabsIntent.Builder()
//            .setDefaultColorSchemeParams(params)
//            .build()
//            .launchUrl(this, Uri.parse(url))
    }

    private fun onLastPostReached(postId: String?) {
        viewModel.getTopPosts(postId)
    }

    override fun injectDependencies() =
        (application as FeatureComponentBuilderProvider).provideFeatureComponentBuilder()
            .build()
            .postsComponentBuilder()
            .postsActivity(this)
            .build()
            .inject(this)
}
