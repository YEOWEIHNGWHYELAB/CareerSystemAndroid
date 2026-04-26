package com.whyelab.careersystemandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.whyelab.careersystemandroid.ui.screen.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen()
        }
    }
}