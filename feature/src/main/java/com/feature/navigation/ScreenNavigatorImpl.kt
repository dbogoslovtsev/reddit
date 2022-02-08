package com.feature.navigation

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import com.core.PresentationConstants
import com.feature.comments.CommentsActivity
import com.reddit.feature.R
import javax.inject.Inject

class ScreenNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity,
    private val resources: Resources
) : ScreenNavigator {

    override fun navigateBack() {
        activity.onBackPressed()
    }

    override fun navigatePostComments(postId: String) {
        activity.startActivity(
            Intent(activity, CommentsActivity::class.java),
            bundleOf(PresentationConstants.ARG_POST_ID to postId)
        )
    }

    override fun navigatePostDetails(postId: String) {
        TODO("Not yet implemented")
    }

    override fun navigateUrl(url: String) {
        val params = CustomTabColorSchemeParams.Builder()
            .setToolbarColor(resources.getColor(R.color.colorPrimary, null))
            .build()
        CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(params)
            .build()
            .launchUrl(activity, url.toUri())
    }
}