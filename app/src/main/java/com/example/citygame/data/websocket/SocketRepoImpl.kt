package com.example.citygame.data.websocket

import com.example.citygame.domain.repositories.SocketRepository
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.filterIsInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketRepoImpl @Inject constructor() : SocketRepository {

    private lateinit var socket: Socket

    init {
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    @ExperimentalCoroutinesApi
    private val eventsFlow = callbackFlow<SocketEvent> {

        socket = SocketHandler.socket()

        socket.on("city") { args ->
            args[0]?.let { word ->
                val city = SocketEvent.CityEvent(word.toString())
                trySend(city)
            }
        }
        socket.on("counter") { args ->
            args[0]?.let { stepNumber ->
                val counter = SocketEvent.CounterEvent(stepNumber.toString())
                trySend(counter)
            }
        }
        awaitClose { channel.close() }
    }

    @ExperimentalCoroutinesApi
    private val counterFlow = eventsFlow.filterIsInstance<SocketEvent.CounterEvent>()

    @ExperimentalCoroutinesApi
    private val cityFlow = eventsFlow.filterIsInstance<SocketEvent.CityEvent>()

    override fun getCounterFlow() = counterFlow

    override fun getCityFlow() = cityFlow

    override fun sendCity(city: String): Emitter = socket.emit("city", city)

    override fun updateCounter(): Emitter = socket.emit("counter")

}

sealed class SocketEvent() {
    class CityEvent(val word: String) : SocketEvent()
    class CounterEvent(val count: String) : SocketEvent()
}