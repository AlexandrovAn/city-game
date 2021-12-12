package com.example.citygame.domain.repositories

import com.example.citygame.data.websocket.SocketEvent
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.Flow

interface SocketRepository {

   fun getCounterFlow(): Flow<SocketEvent.CounterEvent>

   fun getCityFlow(): Flow<SocketEvent.CityEvent>

   fun sendCity(city : String) : Emitter

   fun updateCounter() : Emitter

}