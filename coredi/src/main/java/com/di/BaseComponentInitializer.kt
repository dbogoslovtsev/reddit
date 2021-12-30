package com.di


abstract class BaseComponentInitializer<SCREEN, EXTRA>{

    protected abstract fun getBuilder(): BaseComponentBuilder<SCREEN, EXTRA>

    fun initialize(provider: ApplicationComponentProvider, params: EXTRA): BaseScreenComponent<SCREEN> {
        return getBuilder()
            .appProvider(provider)
            .extra(params)
            .build()
    }

}