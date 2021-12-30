package com.feature.main

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.domain.usecase.GetTopPostsUc
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    fun presenter(getTopPostsUc: GetTopPostsUc): MainContract.Presenter {
        return MainPresenter(getTopPostsUc)
    }

    @Provides
    fun layoutInflater(activity: AppCompatActivity) = LayoutInflater.from(activity)

    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager
}