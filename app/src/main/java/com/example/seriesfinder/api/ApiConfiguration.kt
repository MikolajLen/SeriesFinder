package com.example.seriesfinder.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiConfiguration {

    fun provideConfigutarion(baseUrl: String) =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}
