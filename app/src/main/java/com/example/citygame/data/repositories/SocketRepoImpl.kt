package com.example.citygame.data.repositories

import com.example.citygame.data.websocket.SocketHandler
import com.example.citygame.domain.repositories.SocketRepository
import io.socket.client.Socket
import io.socket.emitter.Emitter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketRepoImpl @Inject constructor() : SocketRepository {

    private lateinit var socket: Socket
    private var idEvent = SocketEvent.IdEvent("")

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
        socket.on("clientId") { args ->
            args[0]?.let { clientId ->
                val id = SocketEvent.IdEvent(clientId.toString())
                if (idEvent.clientId.isEmpty()) idEvent = id
                trySend(idEvent)
            }
        }
        socket.on("endGame") { args ->
            if (args.isNotEmpty()) {
                args[0]?.let { loser ->
                    val loser = SocketEvent.LoserEvent(loser.toString())
                    trySend(loser)
                }
            }
        }
        awaitClose { channel.close() }
    }.flowOn(Dispatchers.IO)

    @ExperimentalCoroutinesApi
    private val counterFlow = eventsFlow.filterIsInstance<SocketEvent.CounterEvent>()

    @ExperimentalCoroutinesApi
    private val cityFlow = eventsFlow.filterIsInstance<SocketEvent.CityEvent>()

    @ExperimentalCoroutinesApi
    private val idFlow = eventsFlow.filterIsInstance<SocketEvent.IdEvent>()

    @ExperimentalCoroutinesApi
    private val loserFlow = eventsFlow.filterIsInstance<SocketEvent.LoserEvent>()

    override fun getCounterFlow() = counterFlow

    override fun getCityFlow() = cityFlow

    override fun getClientIdFlow() = idFlow

    override fun getLoserEventFlow() = loserFlow

    override fun sendCity(city: String): Emitter = socket.emit("city", city)

    override fun updateCounter(): Emitter = socket.emit("counter")

    override fun surrenderEvent(loserId: String): Emitter = socket.emit("surrender", loserId)

}

sealed class SocketEvent() {
    class CityEvent(val word: String) : SocketEvent()
    class CounterEvent(val count: String) : SocketEvent()
    class IdEvent(val clientId: String) : SocketEvent()
    class LoserEvent(val loserId: String) : SocketEvent()
}