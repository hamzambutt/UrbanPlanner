package com.SemiColon.urbanplanner.network

import android.util.Log
import com.SemiColon.urbanplanner.SupabaseClient
import com.SemiColon.urbanplanner.network.models.ProfileResponse
import io.github.jan.supabase.gotrue.auth
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.json.jsonPrimitive

class ProfileRepository {

    suspend fun getMyProfile(): ProfileResponse? {
        return try {
            val response = ApiClient.client.get("${ApiClient.BASE_URL}/api/v1/profiles/me")
            if (response.status.value in 200..299) {
                response.body<ProfileResponse>()
            } else {
                Log.w("ProfileRepo", "Backend returned status ${response.status}, falling back to Supabase session")
                getFallbackProfileFromSupabase()
            }
        } catch (e: Exception) {
            Log.w("ProfileRepo", "Failed to reach backend /api/v1/profiles/me: ${e.message}. Using Supabase session.")
            getFallbackProfileFromSupabase()
        }
    }

    private fun getFallbackProfileFromSupabase(): ProfileResponse? {
        return try {
            val user = SupabaseClient.client.auth.currentUserOrNull() ?: return null
            val metadata = user.userMetadata
            val fullName = metadata?.get("full_name")?.jsonPrimitive?.content 
                ?: metadata?.get("name")?.jsonPrimitive?.content 
                ?: user.email?.substringBefore("@")?.replaceFirstChar { it.uppercase() }
                ?: "Urban Planner User"
            val orgName = metadata?.get("organization_name")?.jsonPrimitive?.content ?: "Independent"
            val role = metadata?.get("role")?.jsonPrimitive?.content ?: "b2c"

            ProfileResponse(
                id = user.id,
                fullName = fullName,
                organizationName = orgName,
                role = role,
                status = "active"
            )
        } catch (e: Exception) {
            Log.e("ProfileRepo", "Error creating fallback profile: ${e.message}")
            null
        }
    }
}
