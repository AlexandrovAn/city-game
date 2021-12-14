package com.example.citygame.data.api

import com.example.citygame.data.entities.City
import retrofit2.http.GET

interface CitiesService {

    @GET("0722a93c35dfba96337b/raw/435b297ac6d90d13a68935e1ec7a69a225969e58/russia")
    suspend fun getCities() : List<City>
}