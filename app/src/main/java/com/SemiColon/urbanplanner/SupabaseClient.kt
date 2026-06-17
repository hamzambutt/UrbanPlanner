package com.SemiColon.urbanplanner

import android.content.Context
import androidx.core.content.edit
import com.SemiColon.urbanplanner.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.SessionManager
import io.github.jan.supabase.gotrue.user.UserSession
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SupabaseClient {
    private val SUPABASE_URL = BuildConfig.SUPABASE_URL
    private val SUPABASE_KEY = BuildConfig.SUPABASE_KEY

    lateinit var client: SupabaseClient

    fun initialize(context: Context) {
        client = createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_KEY
        ) {
            install(Auth) {
                // Tells Supabase to save login details to this file
                sessionManager = AndroidSessionManager(context)
            }
        }
    }
}

// --- Helper Class to Save/Load Login Data ---
class AndroidSessionManager(context: Context) : SessionManager {
    private val prefs = context.getSharedPreferences("supabase_auth", Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun saveSession(session: UserSession) {
        val sessionStr = json.encodeToString(session)
        // Clean KTX syntax (Auto-applies changes)
        prefs.edit {
            putString("session", sessionStr)
        }
    }

    override suspend fun loadSession(): UserSession? {
        val sessionStr = prefs.getString("session", null) ?: return null
        return try {
            json.decodeFromString(sessionStr)
        } catch (e: Exception) {
            android.util.Log.e("SupabaseAuth", "Failed to load saved session", e)
            null
        }
    }

    override suspend fun deleteSession() {
        // Clean KTX syntax (Auto-applies changes)
        prefs.edit {
            remove("session")
        }
    }
}