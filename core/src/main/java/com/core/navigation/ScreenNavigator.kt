package com.core.navigation

interface ScreenNavigator {
    fun navigateBack()
    fun navigatePostComments(postId: String)
    fun navigatePostDetails(postId: String)
    fun navigateUrl(url: String)
}