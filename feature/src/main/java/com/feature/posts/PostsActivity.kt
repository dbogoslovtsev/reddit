package com.feature.posts

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.core.base.BaseActivity
import com.core.customview.HorizontalItemDecorator
import com.feature.di.FeatureComponentBuilderProvider
import com.feature.navigation.ScreenNavigator
import com.reddit.feature.R
import com.reddit.feature.databinding.ActivityPostsBinding
import javax.inject.Inject

class PostsActivity : BaseActivity<PostsViewModel>() {

    @Inject
    lateinit var screenNavigator: ScreenNavigator

    private lateinit var binding: ActivityPostsBinding

    private val adapter = PostAdapter(::onPostClicked, ::onCommentsClicked, ::onLastPostReached)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

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

    private fun onPostClicked(url: String) {
        screenNavigator.navigatePostDetails(url)
    }

    private fun onCommentsClicked(postId: String) {
        screenNavigator.navigatePostComments(postId)
    }

    private fun onLastPostReached(postId: String?) {
        viewModel.getTopPosts(postId)
    }

    override fun injectDependencies() =
        (application as FeatureComponentBuilderProvider).featureComponentBuilder().build()
            .activityComponentBuilder()
            .activity(this)
            .build()
            .presentationComponentBuilder()
            .savedStateRegistryOwner(this)
            .build()
            .inject(this)
}
