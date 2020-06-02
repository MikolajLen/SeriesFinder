package com.example.seriesfinder.di

import com.example.seriesfinder.api.ApiConfiguration
import com.example.seriesfinder.api.SearchService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiCofiguration(@BaseUrl baseUrl: String) =
        ApiConfiguration().provideConfigutarion(baseUrl)

    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit) = retrofit.create(SearchService::class.java)
}
