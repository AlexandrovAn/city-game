package com.example.citygame.domain.repositories

interface CitiesRepository {

    suspend fun validation(word: String) : Boolean

}