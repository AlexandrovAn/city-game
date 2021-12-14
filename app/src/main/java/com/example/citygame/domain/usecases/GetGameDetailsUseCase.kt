package com.example.citygame.domain.usecases

import com.example.citygame.domain.repositories.SocketRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val repo : SocketRepository
) {

    fun getCounterFlow() = repo.getCounterFlow()

    fun getCityFlow() = repo.getCityFlow()

    fun getClientIdFlow() = repo.getClientIdFlow()

    fun getLoserFlow() = repo.getLoserEventFlow()

}