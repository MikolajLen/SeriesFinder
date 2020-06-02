package com.example.seriesfinder.api.model

data class Show(
    val genres: List<String>,
    val id: Int,
    val image: Image?,
    val name: String
)
