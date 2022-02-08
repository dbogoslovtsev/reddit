package com.feature.comments

import com.core.base.BaseActivity
import com.feature.di.FeatureComponentBuilderProvider
import com.feature.navigation.ScreenNavigator
import javax.inject.Inject

class CommentsActivity : BaseActivity<CommentsViewModel>() {

    @Inject
    lateinit var screenNavigator: ScreenNavigator


    override fun onStart() {
        super.onStart()
        showToast("Second module loaded")
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