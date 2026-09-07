package com.SemiColon.urbanplanner.agent

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import android.util.Log
import com.SemiColon.urbanplanner.BuildConfig
import kotlin.coroutines.cancellation.CancellationException
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.gotrue.auth

class AgentRepository(private val supabase: SupabaseClient = com.SemiColon.urbanplanner.SupabaseClient.client) {

    private val generativeModel = GenerativeModel(
        modelName = "gemini-3.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY,
        systemInstruction = content {
            text(
                """
                You are GeoAI, a specialized digital assistant for the 'Urban Planner' application. 
                Your expertise is strictly limited to urban planning, land analysis, solar potential, 
                geographic hazards, real estate compliance, and city livability.
                
                CRITICAL RULES:
                1. You must ONLY answer questions related to land, geography, and urban planning.
                2. If a user asks about ANY unrelated topic (such as food, sports, movies, general trivia, or personal advice), you must politely refuse to answer.
                3. When refusing, briefly remind the user that you are specifically an Urban Planning assistant.
                4. Keep your answers concise and professional.
                """.trimIndent()
            )
        }
    )

    suspend fun sendMessage(query: String): String? {
        return try {
            val response = generativeModel.generateContent(query)
            response.text
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e("AgentAPI", "Gemini error: ${e.message}", e)
            null
        }
    }

    suspend fun fetchChatHistory(): List<ChatMessage> {
        return try {
            val currentUser = supabase.auth.currentUserOrNull()?.id ?: return emptyList()
            
            val entities = supabase.postgrest["chat_messages"]
                .select { 
                    filter { eq("user_id", currentUser) }
                    order("created_at", order = io.github.jan.supabase.postgrest.query.Order.ASCENDING)
                }
                .decodeList<ChatMessageEntity>()

            entities.map { 
                ChatMessage(
                    id = it.id ?: "", 
                    text = it.text, 
                    isUser = it.isUser
                ) 
            }
        } catch (e: Exception) {
            Log.e("AgentAPI", "Failed to load history: ${e.message}")
            emptyList()
        }
    }

    suspend fun saveMessageToDb(text: String, isUser: Boolean) {
        try {
            val currentUser = supabase.auth.currentUserOrNull()?.id ?: return
            
            val entity = ChatMessageEntity(
                userId = currentUser,
                text = text,
                isUser = isUser
            )
            
            supabase.postgrest["chat_messages"].insert(entity)
        } catch (e: Exception) {
            Log.e("AgentAPI", "Failed to save message: ${e.message}")
        }
    }
}