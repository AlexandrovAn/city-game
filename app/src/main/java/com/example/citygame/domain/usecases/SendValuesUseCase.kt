package com.example.citygame.domain.usecases

import com.example.citygame.domain.repositories.SocketRepository
import javax.inject.Inject

class SendValuesUseCase @Inject constructor(
    private val repo: SocketRepository
) {

    fun sendValues(city: String) {
        repo.sendCity(city)
        repo.updateCounter()
    }

    fun endGame(loser: String) = repo.surrenderEvent(loser)

}