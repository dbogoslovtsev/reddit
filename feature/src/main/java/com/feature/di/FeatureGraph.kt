package com.feature.di

import android.content.res.Resources
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.core.di.viewmodel.ViewModelKey
import com.domain.di.scope.ActivityScope
import com.domain.di.scope.FeatureScope
import com.domain.di.scope.PresentationScope
import com.feature.comments.CommentsActivity
import com.feature.comments.CommentsViewModel
import com.feature.navigation.ScreenNavigator
import com.feature.navigation.ScreenNavigatorImpl
import com.feature.posts.PostsActivity
import com.feature.posts.PostsViewModel
import dagger.*
import dagger.multibindings.IntoMap

/**
 * Provides FeatureComponent back from the 'app' module
 * to keep application level entities within standalone project module
 * **/
interface FeatureComponentBuilderProvider {
    fun featureComponentBuilder(): FeatureComponent.Builder
}

/**
 * Feature scope can be treated as an ApplicationScope in
 * a simple single-module application
 * **/
@FeatureScope
@Subcomponent(modules = [FeatureModule::class])
interface FeatureComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FeatureComponent
    }

    fun activityComponentBuilder(): ActivityComponent.Builder
}

@Module(subcomponents = [ActivityComponent::class])
abstract class FeatureModule

/** Activity scope **/
@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: AppCompatActivity): Builder
        fun build(): ActivityComponent
    }

    fun presentationComponentBuilder(): PresentationComponent.Builder
}

@Module(subcomponents = [PresentationComponent::class])
class ActivityModule {

    @Provides
    fun screensNavigator(activity: AppCompatActivity, resources: Resources): ScreenNavigator =
        ScreenNavigatorImpl(activity, resources)

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater =
        LayoutInflater.from(activity)

    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
}

/** Presentation scope **/
@PresentationScope
@Subcomponent(modules = [PresentationModule::class, ViewModelModule::class])
interface PresentationComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun savedStateRegistryOwner(savedStateRegistryOwner: SavedStateRegistryOwner): Builder
        fun build(): PresentationComponent
    }

    fun inject(postsActivity: PostsActivity)
    fun inject(commentsActivity: CommentsActivity)
}

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModelClass(): Class<PostsViewModel> = PostsViewModel::class.java

    @Provides
    fun provideCommentsViewModelClass(): Class<CommentsViewModel> =
        CommentsViewModel::class.java
}

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