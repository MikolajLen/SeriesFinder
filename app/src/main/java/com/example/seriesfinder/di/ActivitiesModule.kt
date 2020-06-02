package com.example.seriesfinder.di

import com.example.seriesfinder.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity
}
