package com.example.citygame.data.websocket

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

object SocketHandler {

    private lateinit var socket: Socket

    @Synchronized
    fun setSocket() {
        try {
            socket = IO.socket("http://192.168.1.103:3000")
        } catch (e: URISyntaxException) {
            Log.e("Socket error", e.toString())
        }
    }

    @Synchronized
    fun socket() = socket

    @Synchronized
    fun establishConnection() = socket.connect()

    @Synchronized
    fun closeConnection() = socket.disconnect()

}