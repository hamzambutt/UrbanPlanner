package com.SemiColon.urbanplanner.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToDashboard: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    // 1. Show a loading spinner while checking
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }

    // 2. The Logic Check
    LaunchedEffect(Unit) {
        // Optional: Small delay so the screen doesn't flicker too fast
        delay(500)

        // Ask Supabase: "Do we have a saved user session?"
        val session = SupabaseClient.client.auth.currentSessionOrNull()

        if (session != null) {
            // YES -> User is registered. Go to Dashboard.
            onNavigateToDashboard()
        } else {
            // NO -> No user found. Go to Login.
            onNavigateToLogin()
        }
    }
}