package com.example.citygame.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.citygame.domain.usecases.GetGameDetailsUseCase
import com.example.citygame.domain.usecases.SendValuesUseCase
import com.example.citygame.presentation.utils.combineNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getDataUseCase: GetGameDetailsUseCase,
    private val sendValuesUseCase: SendValuesUseCase
) : ViewModel() {


    val word = getDataUseCase.getCityFlow().map { it.word }.asLiveData()


    val counter = getDataUseCase.getCounterFlow().map { it.count }.asLiveData()


    val clientId = getDataUseCase.getClientIdFlow().map { it.clientId }.asLiveData()

    val loser = getDataUseCase.getLoserFlow().map { it.loserId }.asLiveData()

    val endGameEvent = clientId.combineNotNull(loser) { id, loserId -> id == loserId }

    fun sendValues(city: String) = sendValuesUseCase.sendValues(city)

    fun endGame(loser: String) = sendValuesUseCase.endGame(loser)

}