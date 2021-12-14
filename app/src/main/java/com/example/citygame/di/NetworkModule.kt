package com.example.citygame.di

import com.example.citygame.data.api.CitiesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/gorborukov/")
            .addConverterFactory(GsonConverterFactory.create())
    }
    private val citiesService: CitiesService by lazy {
        retrofitBuilder.build().create(CitiesService::class.java)
    }

    @Provides
    fun citiesService() = citiesService

}