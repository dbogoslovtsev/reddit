package com.feature.di

import androidx.savedstate.SavedStateRegistryOwner
import com.core.base.BaseViewModel
import com.domain.di.scope.ActivityScope
import com.feature.main.MainActivity
import com.feature.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module(subcomponents = [MainComponent::class])
class FeatureModule

/** Main Screen **/
interface MainComponentFactoryProvider {
    fun provideMainComponentFactory(): MainComponent.Factory
}

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(module: MainModule): MainComponent
    }

    fun inject(activity: MainActivity)
}

@Module
class MainModule(private val mainActivity: MainActivity) {

    @ActivityScope
    @Provides
    fun provideSavedStateRegistryOwner(): SavedStateRegistryOwner = mainActivity

    @ActivityScope
    @Provides
    fun provideMainViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
}

/** Details Screen **/
