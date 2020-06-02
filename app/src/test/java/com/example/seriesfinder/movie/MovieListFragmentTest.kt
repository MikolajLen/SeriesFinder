package com.example.seriesfinder.movie

import android.view.View
import android.widget.SearchView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.seriesfinder.R
import com.example.seriesfinder.api.model.MoviesResponseItem
import com.example.seriesfinder.api.model.Show
import com.example.seriesfinder.di.SeriesFinderApplication
import com.example.seriesfinder.movie.adapter.MovieListAdapter
import com.example.seriesfinder.movie.viewModel.MovieListViewModel
import com.example.seriesfinder.movie.viewModel.MovieListViewModelFactory
import com.example.seriesfinder.utils.NavigateAction
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import di.DaggerTestMovieListFragmentComponent
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class MovieListFragmentTest {

    @Inject
    lateinit var viewModelFactory: MovieListViewModelFactory

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    private val mutableMovieList = MutableLiveData<List<MoviesResponseItem>>()
    private val singleNavigateEvent = MutableLiveData<NavigateAction>()

    private val viewModel = mock<MovieListViewModel> {
        whenever(it.movieList).thenReturn(mutableMovieList)
        whenever(it.navigationEvent).thenReturn(singleNavigateEvent)
    }

    @Before
    fun setUp() {
        val app = ApplicationProvider.getApplicationContext<SeriesFinderApplication>()
        val component = DaggerTestMovieListFragmentComponent.create()
        component.inject(app)
        component.inject(this)
        whenever(viewModelFactory.create(MovieListViewModel::class.java)).thenReturn(viewModel)
    }

    @Test
    fun `should set item to the adapter`() {
        //given
        val testResponse = MoviesResponseItem(
            score = 5.0,
            show = Show(genres = listOf("drama"), name = "show", id = 1, image = null)
        )
        val moviesList = listOf(testResponse)
        mutableMovieList.value = moviesList

        //when
        launchFragmentInContainer<MovieListFragment>(themeResId = R.style.AppTheme)

        //then
        verify(movieListAdapter).setItems(moviesList)
    }

    @Test
    fun `should navigate when navigation event received`() {
        //given
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)
        val scenario =
            launchFragmentInContainer<MovieListFragment>(themeResId = R.style.AppTheme)
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        //when
        singleNavigateEvent.value = NavigateAction()

        //then
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.moviePlayerFragment)

    }

    @Test
    fun `should perform search when text inserted`() {
        //given
        launchFragmentInContainer<MovieListFragment>(themeResId = R.style.AppTheme)

        //when
        onView(withId(R.id.search_box)).perform(typeSearchViewText("search"))

        //then
        verify(viewModel).searchForTheMovies("search")

    }
}

fun typeSearchViewText(text: String): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String {
            return "Change view text"
        }

        override fun getConstraints(): Matcher<View> {
            return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
        }

        override fun perform(uiController: UiController?, view: View?) {
            (view as SearchView).setQuery(text, false)
        }
    }
}
