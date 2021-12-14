package com.example.citygame.domain.usecases

import com.example.citygame.domain.repositories.SavedWordsRepository
import com.example.citygame.domain.repositories.SocketRepository
import javax.inject.Inject

class SendValuesUseCase @Inject constructor(
    private val socketRepo: SocketRepository,
    private val savedWordsRepo : SavedWordsRepository
) {

    fun sendValues(city: String) {
        socketRepo.sendCity(city)
        socketRepo.updateCounter()
    }

    fun endGame(loser: String) = socketRepo.surrenderEvent(loser)

    suspend fun saveWord(word: String) = savedWordsRepo.saveWord(word)

}