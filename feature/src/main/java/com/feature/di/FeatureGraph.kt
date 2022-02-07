package com.feature.di

import androidx.savedstate.SavedStateRegistryOwner
import com.domain.di.scope.ActivityScope
import com.feature.comments.CommentsActivity
import com.feature.comments.CommentsViewModel
import com.feature.posts.PostsActivity
import com.feature.posts.PostsViewModel
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

interface FeatureComponentBuilderProvider {
    fun provideFeatureComponentBuilder(): FeatureComponent.Builder
}

@Module(subcomponents = [PostsComponent::class, CommentsComponent::class])
class FeatureModule

// TODO: Check the scopes below
//@FeatureScope
@Subcomponent(modules = [FeatureModule::class])
interface FeatureComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): FeatureComponent
    }

    fun postsComponentBuilder(): PostsComponent.Builder
    fun commentsComponentBuilder(): CommentsComponent.Builder
}

/** Posts Screen **/
@Module
class PostsModule {

    //    @ActivityScope
    @Provides
    fun provideSavedStateRegistryOwner(postsActivity: PostsActivity): SavedStateRegistryOwner =
        postsActivity

    //    @ActivityScope
    @Provides
    fun provideMainViewModelClass(): Class<PostsViewModel> = PostsViewModel::class.java
}

//@ActivityScope
@Subcomponent(modules = [PostsModule::class])
interface PostsComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun postsActivity(postsActivity: PostsActivity): Builder
        fun build(): PostsComponent
    }

    fun inject(postsActivity: PostsActivity)
}

/** Comments Screen **/
@Module
class CommentsModule {
    //    @ActivityScope
    @Provides
    fun provideSavedStateRegistryOwner(commentsActivity: CommentsActivity): SavedStateRegistryOwner =
        commentsActivity

    //    @ActivityScope
    @Provides
    fun provideCommentsViewModelClass(): Class<CommentsViewModel> = CommentsViewModel::class.java
}

//@ActivityScope
@Subcomponent(modules = [CommentsModule::class])
interface CommentsComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun commentsActivity(commentsActivity: CommentsActivity): Builder
        fun build(): CommentsComponent
    }

    fun inject(commentsActivity: CommentsActivity)
}
