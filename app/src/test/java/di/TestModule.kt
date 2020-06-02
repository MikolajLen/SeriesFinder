package di

import com.example.seriesfinder.movie.adapter.MovieListAdapter
import com.example.seriesfinder.movie.viewModel.MovieListViewModelFactory
import com.example.seriesfinder.player.SourceProvider
import com.nhaarman.mockitokotlin2.mock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TestModule {

    @Provides
    @Singleton
    fun provideMovieListViewModelFactory(): MovieListViewModelFactory = mock()

    @Provides
    @Singleton
    fun provideMovieListAdapter(): MovieListAdapter = mock()

    @Provides
    @Singleton
    fun provideSourceProvider(): SourceProvider = mock()
}
