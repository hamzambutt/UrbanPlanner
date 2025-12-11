package com.SemiColon.urbanplanner.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.SemiColon.urbanplanner.Login.LoginScreen
import com.SemiColon.urbanplanner.Signup.SignupScreen
import com.SemiColon.urbanplanner.home.HomeScreen

object Routes {
    const val LOGIN_SCREEN = "login_screen"
    const val SIGNUP_SCREEN = "signup_screen"
    const val HOME_SCREEN = "home_screen"
}

@Composable
fun Appnavigator(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN_SCREEN
    ){
        // --- Login Screen Route ---
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(
                onNavigateToSignup = {
                    navController.navigate(Routes.SIGNUP_SCREEN)
                }
            )
        }

        // --- Signup Screen Route ---
        composable(Routes.SIGNUP_SCREEN) {
            SignupScreen(
                onNavigateToLogin = {
                    navController.navigate(Routes.LOGIN_SCREEN) {
                        // Optional: clear the stack so back button doesn't loop
                        popUpTo(Routes.LOGIN_SCREEN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.HOME_SCREEN){
            HomeScreen(
                onLogout={
                    navController.navigate(Routes.LOGIN_SCREEN){
                        popUpTo(Routes.HOME_SCREEN){ inclusive = true}
                    }

                }
            )
        }

    }
}