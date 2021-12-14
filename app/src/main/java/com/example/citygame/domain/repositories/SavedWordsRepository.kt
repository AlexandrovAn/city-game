package com.example.citygame.domain.repositories

interface SavedWordsRepository {

    suspend fun validateRepeatWords(word : String) : Boolean

    suspend fun saveWord(word: String)

}