package com.example.seriesfinder.movie.viewModel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seriesfinder.utils.NavigateAction
import com.example.seriesfinder.api.SearchService
import com.example.seriesfinder.api.model.MoviesResponseItem
import com.example.seriesfinder.utils.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListViewModel(private val apiService: SearchService) : ViewModel() {

    val movieList: LiveData<List<MoviesResponseItem>> get() = mutableMovieList
    val navigationEvent: LiveData<NavigateAction?> get() = singleNavigateEvent

    private val mutableMovieList = MutableLiveData<List<MoviesResponseItem>>()
    private val singleNavigateEvent = SingleLiveEvent<NavigateAction>()

    @VisibleForTesting
    internal var job: Job? = null
        private set

    fun searchForTheMovies(title: String) {
        job?.cancel()
        job = viewModelScope.launch {
            mutableMovieList.value = apiService.search(title)
        }
    }

    fun navigateToDetails() {
        singleNavigateEvent.postValue(NavigateAction())
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}
