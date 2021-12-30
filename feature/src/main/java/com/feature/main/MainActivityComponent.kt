package com.feature.main

import com.di.ApplicationComponentProvider
import com.di.BaseScreenComponent
import dagger.Component

@Component(
    dependencies = [ApplicationComponentProvider::class],
    modules = [MainActivityModule::class]
)
interface MainActivityComponent : BaseScreenComponent<MainActivity> {

    @Component.Factory
    interface Factory {
        fun create(appComponent: ApplicationComponentProvider): MainActivityComponent
    }
}