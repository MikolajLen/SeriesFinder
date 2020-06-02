package com.example.seriesfinder.movie.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.seriesfinder.api.model.MoviesResponseItem

class MovieListCallback(
    val oldList: List<MoviesResponseItem>,
    val newList: List<MoviesResponseItem>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].show?.id == newList[newItemPosition].show?.id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
