package com.example.seriesfinder.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seriesfinder.R
import com.example.seriesfinder.movie.adapter.MovieListAdapter
import com.example.seriesfinder.movie.viewModel.MovieListViewModel
import com.example.seriesfinder.movie.viewModel.MovieListViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.movie_list_layout.*
import javax.inject.Inject

class MovieListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: MovieListViewModelFactory

    @Inject
    lateinit var movieListAdapter: MovieListAdapter

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.movie_list_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        initList()
        subscribeToDataChanges()
    }

    private fun initSearch() {
        search_box.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { text ->
                    viewModel.searchForTheMovies(text)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text ->
                    if (text.length >= 3) viewModel.searchForTheMovies(text)
                }
                return false
            }

        })
    }

    private fun initList() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            adapter = movieListAdapter
        }
        movieListAdapter.itemClickListener = { item -> viewModel.navigateToDetails() }
    }

    private fun subscribeToDataChanges() {
        viewModel.movieList.observe(viewLifecycleOwner, Observer {
            movieListAdapter.setItems(it)
        })
        viewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.navigate_to_player)
        })
    }
}
