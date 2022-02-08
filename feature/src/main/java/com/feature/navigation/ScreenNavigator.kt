package com.feature.navigation

interface ScreenNavigator {
    fun navigateBack()
    fun navigateUrl(url: String)
    fun navigatePostComments(postId: String)
    fun navigatePostDetails(postId: String)
}