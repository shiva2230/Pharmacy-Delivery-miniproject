package com.example.myapplication
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class ChatWebSocketListener : WebSocketListener() {
    override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
        // WebSocket connection opened
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        // Handle received messages from the server
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        // Handle received binary messages from the server
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        // WebSocket connection closed
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
        // Handle connection failure
    }
}
