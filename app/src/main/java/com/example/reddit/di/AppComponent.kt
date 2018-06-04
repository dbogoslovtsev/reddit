package com.example.reddit.di

import com.example.reddit.di.module.*
import com.example.reddit.presentation.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    PresentersModule::class,
    RepositoriesModule::class,
    ExecutionThreadsModule::class,
    RetrofitModule::class,
    KeyStoreModule::class
])
interface AppComponent {

    fun inject(activity: MainActivity)

}