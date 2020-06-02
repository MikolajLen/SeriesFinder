package com.example.seriesfinder.di

import com.example.seriesfinder.movie.MovieListFragment
import com.example.seriesfinder.player.MoviePlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun bindMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun bindMoviePlayerFragment(): MoviePlayerFragment
}
