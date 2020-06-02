package com.example.seriesfinder.movie.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.android.kotlincoroutines.main.utils.MainCoroutineScopeRule
import com.example.android.kotlincoroutines.main.utils.getValueForTest
import com.example.seriesfinder.api.SearchService
import com.example.seriesfinder.api.model.MoviesResponseItem
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScopeRule()

    val testService = mock<SearchService> { }

    lateinit var underTest: MovieListViewModel

    @Before
    fun setUp() {
        underTest = MovieListViewModel(testService)
    }

    @Test
    fun `should post search result to live data`() = coroutineScope.runBlockingTest {
        //given
        val mockedMovie = mock<MoviesResponseItem> { }
        val apiResult = listOf(mockedMovie)
        whenever(testService.search(any())).thenReturn(apiResult)

        //when
        underTest.searchForTheMovies("movies")

        //then
        assertThat(underTest.movieList.getValueForTest()).isEqualTo(apiResult)
    }

    @Test
    fun `should navigate to details`() {
        //when
        underTest.navigateToDetails()

        //then
        assertThat(underTest.navigationEvent.getValueForTest()).isNotNull()
    }
}
