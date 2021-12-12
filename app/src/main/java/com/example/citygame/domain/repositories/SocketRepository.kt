package com.example.citygame.domain.repositories

import com.example.citygame.data.repositories.SocketEvent
import io.socket.emitter.Emitter
import kotlinx.coroutines.flow.Flow

interface SocketRepository {

   fun getCounterFlow(): Flow<SocketEvent.CounterEvent>

   fun getCityFlow(): Flow<SocketEvent.CityEvent>

   fun getClientIdFlow(): Flow<SocketEvent.IdEvent>

   fun getLoserEventFlow() : Flow<SocketEvent.LoserEvent>

   fun sendCity(city : String) : Emitter

   fun updateCounter() : Emitter

   fun surrenderEvent(loserId : String) : Emitter

}