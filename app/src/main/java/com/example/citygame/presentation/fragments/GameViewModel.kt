package com.example.citygame.presentation.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.citygame.domain.usecases.GetGameDetailsUseCase
import com.example.citygame.domain.usecases.SendValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getDataUseCase: GetGameDetailsUseCase,
    private val sendValuesUseCase: SendValuesUseCase
) : ViewModel() {

    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    private val _counter = MutableLiveData<String>()
    val counter: LiveData<String>
        get() = _counter

    fun sendValues(city: String) = sendValuesUseCase.sendValues(city)

    init {

        getDataUseCase.getCityFlow().onEach { event ->
            _word.postValue(event.word)
        }.launchIn(viewModelScope)

        getDataUseCase.getCounterFlow().onEach { event ->
            _counter.postValue(event.count)
        }.launchIn(viewModelScope)

    }

}