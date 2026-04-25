package com.SemiColon.urbanplanner.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.SemiColon.urbanplanner.SupabaseClient
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToDashboard: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    // 1. Show an aesthetic loading screen while checking auth state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = androidx.compose.foundation.shape.CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = "App Logo",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(64.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = "UrbanPlanner",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }

    // 2. The Logic Check
    LaunchedEffect(Unit) {
        // Brief delay so the user can enjoy the splash screen aesthetic
        delay(1200)

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