package com.feature.main.di

import com.core.di.scope.ActivityScope
import com.feature.main.MainActivity
import com.feature.main.MainContract
import com.feature.main.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module(subcomponents = [MainComponent::class])
class FeatureModule

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
class MainModule(private val activity: MainActivity) {

    @ActivityScope
    @Provides
    fun provideMainView(): MainContract.View = activity

    @ActivityScope
    @Provides
    fun provideMainPresenter(presenter: MainPresenter): MainContract.Presenter = presenter

}