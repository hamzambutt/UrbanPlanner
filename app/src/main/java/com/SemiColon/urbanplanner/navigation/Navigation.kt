package com.SemiColon.urbanplanner.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.SemiColon.urbanplanner.DashboardScreen
import com.SemiColon.urbanplanner.login.LoginScreen
import com.SemiColon.urbanplanner.signup.SignupScreen
import com.SemiColon.urbanplanner.map.*

object Routes {
    const val LOGIN_SCREEN = "login_screen"
    const val SIGNUP_SCREEN = "signup_screen"
    const val DASHBOARD_SCREEN = "dashboard_screen"
    const val MAP_SCREEN = "map_screen"
    const val SPLASH_SCREEN = "splash_screen"
}

@Composable
fun AppNavigator(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH_SCREEN
    ){
        // --- Login Screen Route ---
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate(Routes.SIGNUP_SCREEN)
                },
                // UPDATED: Now navigates to Dashboard upon login
                onNavigateToDashboard = {
                    navController.navigate(Routes.DASHBOARD_SCREEN){
                        // Clears back stack so back button doesn't return to login
                        popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        // --- Signup Screen Route ---
        composable(Routes.SIGNUP_SCREEN) {
            SignupScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN_SCREEN) {
                        popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }

        // --- Dashboard Screen Route---
        composable(Routes.DASHBOARD_SCREEN){
            DashboardScreen(
                onNavigateToMap = {
                    navController.navigate(Routes.MAP_SCREEN)
                },
                onNavigateToSaved = {
                    // Create a route for this later if needed
                    // navController.navigate("saved_screen")
                },
                onNavigateToSettings = {
                    // Create a route for this later if needed
                    // navController.navigate("settings_screen")
                }
            )
        }

        // --- Map Screen Route ---
        composable(Routes.MAP_SCREEN) {
            MapsScreen()
        }
        // --- Splash Screen Route ---
        composable("splash_screen") {
            SplashScreen(
                onNavigateToDashboard = {
                    // Navigate to Dashboard and clear history so "Back" doesn't return to Splash
                    navController.navigate("dashboard_screen") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate("login_screen") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            )
        }
    }
}