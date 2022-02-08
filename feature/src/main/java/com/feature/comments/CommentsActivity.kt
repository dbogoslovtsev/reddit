package com.feature.comments

import com.core.base.BaseActivity
import com.feature.di.FeatureComponentBuilderProvider

class CommentsActivity : BaseActivity<CommentsViewModel>() {

    override fun onStart() {
        super.onStart()
        showToast("Second module loaded")
    }

    // TODO: Change this boilerplate
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