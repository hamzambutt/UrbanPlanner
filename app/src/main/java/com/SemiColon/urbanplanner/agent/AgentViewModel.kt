package com.SemiColon.urbanplanner.agent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AgentViewModel : ViewModel() {
    
    private val repository = AgentRepository()

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _isLoading.value = true
            val history = withContext(Dispatchers.IO) {
                repository.fetchChatHistory()
            }
            _messages.value = history
            _isLoading.value = false
        }
    }

    fun sendMessage(query: String) {
        if (query.isBlank()) return

        // 1. Update UI immediately
        val userMessage = ChatMessage(text = query, isUser = true)
        _messages.update { it + userMessage }

        viewModelScope.launch {
            _isLoading.value = true

            // 2. Save user message to Supabase
            launch(Dispatchers.IO) { repository.saveMessageToDb(query, true) }

            // 3. Request Gemini response
            val responseText = withContext(Dispatchers.IO) {
                repository.sendMessage(query)
            }

            if (responseText != null) {
                // 4. Update UI with AI response
                _messages.update {
                    it + ChatMessage(text = responseText, isUser = false)
                }
                // 5. Save AI response to Supabase
                launch(Dispatchers.IO) { repository.saveMessageToDb(responseText, false) }
            } else {
                _messages.update {
                    it + ChatMessage(
                        text = "Connection failed. Please check your network.",
                        isUser = false
                    )
                }
            }
            _isLoading.value = false
        }
    }
    
    fun clearSession() {
        _messages.value = emptyList()
    }
}
