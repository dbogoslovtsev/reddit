package com.reddit.di

import android.content.Context
import com.core.di.scope.ApplicationScope
import com.data.di.module.ExecutionThreadsModule
import com.data.di.module.KeyStoreModule
import com.data.di.module.RepositoryModule
import com.data.di.module.RetrofitModule
import com.domain.di.module.UseCaseModule
import com.feature.main.di.FeatureModule
import com.feature.main.di.MainComponent
import dagger.Component
import dagger.Module
import dagger.Provides

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        FeatureModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        KeyStoreModule::class,
        RetrofitModule::class,
        ExecutionThreadsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(modules: AppModule): AppComponent
    }

    // downstream dependent components need data types to be exposed
    // 'subcomponents' do not need this exposure (i.e. 'Context' is automatically reachable)
    fun mainComponentFactory(): MainComponent.Factory

}

@Module
class AppModule(private val context: Context) {

    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context = context

}