package com.reddit.di

import androidx.lifecycle.ViewModel
import com.core.di.viewmodel.ViewModelKey
import com.feature.comments.CommentsViewModel
import com.feature.posts.PostsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentsViewModel::class)
    abstract fun bindCommentsViewModel(commentsViewModel: CommentsViewModel): ViewModel
}