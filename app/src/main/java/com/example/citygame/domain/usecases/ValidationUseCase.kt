package com.example.citygame.domain.usecases

import com.example.citygame.domain.repositories.CitiesRepository
import com.example.citygame.domain.repositories.SavedWordsRepository
import javax.inject.Inject

class ValidationUseCase @Inject constructor(
    private val cityRepo: CitiesRepository,
    private val savedWordsRepo : SavedWordsRepository
) {

    suspend fun validateCity(word: String) = cityRepo.validation(word)

    suspend fun validateRepeatWord(word: String) = savedWordsRepo.validateRepeatWords(word)

}