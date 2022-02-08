package com.reddit.di

import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import com.data.di.module.KeyStoreModule
import com.data.di.module.RepositoryModule
import com.data.di.module.RetrofitModule
import com.domain.di.module.UseCaseModule
import com.domain.di.scope.ApplicationScope
import com.feature.di.FeatureComponent
import dagger.Component
import dagger.Module
import dagger.Provides

@ApplicationScope
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        NavigationModule::class,
        UseCaseModule::class,
        KeyStoreModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(modules: AppModule): AppComponent
    }

    // Downstream dependent components need data types to be exposed
    // Subcomponents do not need this exposure (i.e. 'Context' is automatically reachable)
    fun featureComponentBuilder(): FeatureComponent.Builder
}

@Module(subcomponents = [FeatureComponent::class])
class AppModule(private val context: Context) {

    @ApplicationScope
    @Provides
    fun provideApplicationContext(): Context = context

    @ApplicationScope
    @Provides
    fun provideContentResolver(): ContentResolver = context.contentResolver

    @ApplicationScope
    @Provides
    fun provideResources(): Resources = context.resources
}