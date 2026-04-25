package com.SemiColon.urbanplanner.navigation
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import com.SemiColon.urbanplanner.DashboardScreen
import com.SemiColon.urbanplanner.Screens.Settings
import com.SemiColon.urbanplanner.login.LoginScreen
import com.SemiColon.urbanplanner.map.MapsScreen
import com.SemiColon.urbanplanner.signup.SignupScreen
import com.SemiColon.urbanplanner.utils.PreferencesManager
object Routes {
    const val LOGIN_SCREEN = "login_screen"
    const val SIGNUP_SCREEN = "signup_screen"
    const val DASHBOARD_SCREEN = "dashboard_screen"
    const val MAP_SCREEN = "map_screen"
    const val SPLASH_SCREEN = "splash_screen"
    const val SETTINGS_SCREEN = "settings_screen"
}

@Composable
fun AppNavigation(navController: NavHostController, preferencesManager: PreferencesManager) {
    // Get current destination to highlight the correct bottom nav item
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    // List of screens that should NOT show the bottom bar (e.g., Splash, Login, Signup)
    val hideBottomBarRoutes = listOf(Routes.SPLASH_SCREEN, Routes.LOGIN_SCREEN, Routes.SIGNUP_SCREEN)
    Scaffold(
        bottomBar = {
            // Only show the BottomBar if we are on a main app screen
            if (currentRoute !in hideBottomBarRoutes) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Routes.DASHBOARD_SCREEN,
                        onClick = {
                            navController.navigate(Routes.DASHBOARD_SCREEN) {
                                popUpTo(Routes.DASHBOARD_SCREEN) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text("Home") },
                        icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Routes.MAP_SCREEN,
                        onClick = {
                            navController.navigate(Routes.MAP_SCREEN) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text("Map") },
                        icon = { Icon(Icons.Outlined.LocationOn, contentDescription = "Map") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Routes.SETTINGS_SCREEN,
                        onClick = {
                            navController.navigate(Routes.SETTINGS_SCREEN) {
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        label = { Text("Settings") },
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = "Settings") }
                    )
                }
            }
        }
    ) { innerPadding ->
        // The innerPadding ensures content (like the Map) isn't covered by the BottomBar
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH_SCREEN,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(animationSpec = tween(300)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300)) },
            exitTransition = { fadeOut(animationSpec = tween(300)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(300)) },
            popEnterTransition = { fadeIn(animationSpec = tween(300)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(300)) },
            popExitTransition = { fadeOut(animationSpec = tween(300)) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(300)) }
        ) {
            // --- Login Screen Route ---
            composable(Routes.LOGIN_SCREEN) {
                LoginScreen(
                    onNavigateToSignup = { navController.navigate(Routes.SIGNUP_SCREEN) },
                    onNavigateToDashboard = {
                        navController.navigate(Routes.DASHBOARD_SCREEN) {
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
            // --- Dashboard Screen Route ---
            composable(Routes.DASHBOARD_SCREEN) {
                DashboardScreen(
                    onNavigateToMap = { navController.navigate(Routes.MAP_SCREEN) }
                )
            }
            // --- Map Screen Route ---
            composable(Routes.MAP_SCREEN) {
                MapsScreen()
            }
            // --- Settings Screen Route ---
            composable(Routes.SETTINGS_SCREEN) {
                Settings(navController = navController, preferencesManager = preferencesManager)
            }
            // --- Splash Screen Route ---
            composable(Routes.SPLASH_SCREEN) {
                SplashScreen(
                    onNavigateToDashboard = {
                        navController.navigate(Routes.DASHBOARD_SCREEN) {
                            popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
                        }
                    },
                    onNavigateToLogin = {
                        navController.navigate(Routes.LOGIN_SCREEN) {
                            popUpTo(Routes.SPLASH_SCREEN) { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}
