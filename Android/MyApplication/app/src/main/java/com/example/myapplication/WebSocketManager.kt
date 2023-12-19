package com.example.myapplication
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketManager(private val listener: ChatWebSocketListener) {
    private var webSocket: WebSocket? = null

    fun connectWebSocket() {
        val client = OkHttpClient.Builder()
            .build()

        val request = Request.Builder()
            .url("http://localhost:8080/ws")
            .build()

        webSocket = client.newWebSocket(request, listener)
    }

    fun sendMessage(message: String) {
        webSocket?.send(message)
    }

    fun disconnectWebSocket() {
        webSocket?.close(1000, "Connection closed")
    }
}
