package com.example.seriesfinder.api

import com.example.seriesfinder.api.model.MoviesResponseItem
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("search/shows")
    suspend fun search(@Query("q") query: String): List<MoviesResponseItem>
}
