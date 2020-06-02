package com.example.seriesfinder.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.seriesfinder.R
import com.example.seriesfinder.api.model.MoviesResponseItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_list_item.view.*
import javax.inject.Inject

class MovieListAdapter
@Inject
constructor() : RecyclerView.Adapter<MovieItemHolder>() {

    private val items: ArrayList<MoviesResponseItem> = arrayListOf()
    var itemClickListener: (item: MoviesResponseItem) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieItemHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

    fun setItems(newItems: List<MoviesResponseItem>) {
        val diffUtilCallback =
            MovieListCallback(
                items,
                newItems
            )
        val result = DiffUtil.calculateDiff(diffUtilCallback)
        items.clear()
        items.addAll(newItems)
        result.dispatchUpdatesTo(this)
    }
}

class MovieItemHolder(private val v: View) : RecyclerView.ViewHolder(v) {

    private val movieImage = v.movie_image
    private val movieTitle = v.movie_title
    private val moviewGenres = v.movie_genres

    fun bind(item: MoviesResponseItem, clickListener: (item: MoviesResponseItem) -> Unit) {
        movieTitle.text = item.show?.name
        moviewGenres.text = item.show?.genres?.joinToString()
        item.show?.image?.medium?.let { url ->
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.movie_placeholder)
                .into(movieImage)
        }
        v.setOnClickListener { clickListener(item) }
    }
}
