package com.feature.comments

import com.core.base.BaseActivity
import com.feature.di.FeatureComponentBuilderProvider

class CommentsActivity : BaseActivity<CommentsViewModel>() {


    override fun onStart() {
        super.onStart()
        showToast("Second module loaded")
    }

    override fun injectDependencies() =
        (application as FeatureComponentBuilderProvider).provideFeatureComponentBuilder()
            .build()
            .commentsComponentBuilder()
            .commentsActivity(this)
            .build()
            .inject(this)
}