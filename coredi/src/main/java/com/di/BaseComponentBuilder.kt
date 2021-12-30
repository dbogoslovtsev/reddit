package com.di

import dagger.BindsInstance

interface BaseComponentBuilder<SCREEN, EXTRA> {

    fun appProvider(provider: ApplicationComponentProvider): BaseComponentBuilder<SCREEN, EXTRA>

    @BindsInstance
    fun extra(extra: EXTRA): BaseComponentBuilder<SCREEN, EXTRA>

    fun build(): BaseScreenComponent<SCREEN>

}