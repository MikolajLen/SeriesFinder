package com.example.seriesfinder.di

import android.content.Context
import com.example.seriesfinder.player.PlayerModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        ActivitiesModule::class, FragmentsModule::class, NetworkModule::class, PlayerModule::class]
)
interface AppComponent {

    fun inject(application: SeriesFinderApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Context): Builder

        @BindsInstance
        fun baseURl(@BaseUrl baseUrl: String): Builder

        fun build(): AppComponent
    }
}
