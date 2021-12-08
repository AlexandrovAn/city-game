package com.example.citygame.data.websocket

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException
import javax.inject.Singleton

object SocketHandler {

    private lateinit var socket: Socket

    @Synchronized
    fun setSocket(){
        try{
            socket = IO.socket("http://172.20.10.4:3000")
        } catch (e:URISyntaxException){
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