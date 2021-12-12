package com.example.citygame.presentation.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <T, K, R> LiveData<T>.combineNotNull(
    liveData: LiveData<K>,
    combination: (T, K) -> R,
): LiveData<R> {
    return MediatorLiveData<R>().also { mediator ->
        mediator.addSource(this) {
            val otherValue = liveData.value ?: return@addSource
            mediator.value = combination(it, otherValue)
        }
        mediator.addSource(liveData) {
            val otherValue = this.value ?: return@addSource
            mediator.value = combination(otherValue, it)
        }
    }
}