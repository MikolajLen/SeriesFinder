package com.example.seriesfinder.di

import android.app.Application
import com.example.seriesfinder.BuildConfig
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class SeriesFinderApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .baseURl(BuildConfig.BASE_URL)
            .build()
            .inject(this)
    }

    override fun androidInjector() = activityInjector
}
