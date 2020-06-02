package com.example.seriesfinder.movie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seriesfinder.api.SearchService
import javax.inject.Inject

class MovieListViewModelFactory
@Inject
constructor(private val apiService: SearchService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(
            apiService
        ) as T
    }
}
