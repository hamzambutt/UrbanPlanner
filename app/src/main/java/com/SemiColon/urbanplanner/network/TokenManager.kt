package com.SemiColon.urbanplanner.network

import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.Auth

object TokenManager {
    fun getAccessToken(): String? {
        return try {
            val auth = SupabaseClient.client.pluginManager.getPlugin(Auth)
            auth.currentAccessTokenOrNull()
        } catch (e: Exception) {
            null
        }
    }
}
