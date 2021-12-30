package com.di

interface BaseScreenComponent<SCREEN> {
    fun inject(screen: SCREEN)
}