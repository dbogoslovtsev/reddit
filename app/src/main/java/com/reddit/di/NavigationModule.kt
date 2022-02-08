package com.reddit.di

import com.core.navigation.ScreenNavigator
import com.reddit.navigation.ScreenNavigatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {
    @Binds
    abstract fun bindScreenNavigator(screenNavigatorImpl: ScreenNavigatorImpl): ScreenNavigator
}