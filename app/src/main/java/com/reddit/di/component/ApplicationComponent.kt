package com.reddit.di.component

import com.data.di.RetrofitModule
import com.di.AllOtherStuffProvider
import com.di.ApplicationComponentProvider
import com.di.BaseScreenComponent
import com.feature.main.MainActivityComponent
import com.reddit.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        AllOtherStuffProvider::class,
        BaseScreenComponent::class
    ],
    modules = [
        AppModule::class,
//        PresentersModule::class,
        RepositoriesModule::class,
        ExecutionThreadsModule::class,
        RetrofitModule::class,
        KeyStoreModule::class
    ]
)
interface ApplicationComponent : ApplicationComponentProvider {

}