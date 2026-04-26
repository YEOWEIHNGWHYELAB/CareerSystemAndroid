package com.whyelab.careersystemandroid.navigation

import com.whyelab.careersystemandroid.data.local.ConfigManager
import com.whyelab.careersystemandroid.ui.screen.config.ConfigScreen
import com.whyelab.careersystemandroid.ui.screen.home.HomeScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val configManager = remember { ConfigManager(context) }

    val start = if (configManager.hasConfig()) "home" else "config"

    NavHost(navController = navController, startDestination = start) {
        composable("config") {
            ConfigScreen(
                navController = navController,
                onSave = {
                    navController.navigate("home") {
                        popUpTo("config") { inclusive = true }
                    }
                }
            )
        }

        composable("home") {
            HomeScreen(navController)
        }
    }
}