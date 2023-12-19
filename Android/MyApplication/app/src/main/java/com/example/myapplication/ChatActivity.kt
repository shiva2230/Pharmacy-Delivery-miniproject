package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ChatActivity : AppCompatActivity() {
    private lateinit var webSocketManager: WebSocketManager
    private lateinit var messageEditText: EditText
    private lateinit var sendMessageButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)

        webSocketManager = WebSocketManager(ChatWebSocketListener())

        webSocketManager.connectWebSocket()

        messageEditText = findViewById(R.id.messageEditText)
        sendMessageButton = findViewById(R.id.sendMessageButton)

        sendMessageButton.setOnClickListener {
            val message = messageEditText.text.toString()
            webSocketManager.sendMessage(message)
            messageEditText.text.clear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        webSocketManager.disconnectWebSocket()
    }
}
