package com.whyelab.careersystemandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.whyelab.careersystemandroid.ui.theme.CareerSystemAndroidTheme

// --------------------
// Routes
// --------------------
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
}

// --------------------
// Main Activity
// --------------------
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CareerSystemAndroidTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route
                ) {
                    composable(Screen.Home.route) {
                        HomeScreen(navController)
                    }

                    composable(Screen.Detail.route) {
                        DetailScreen(navController)
                    }
                }
            }
        }
    }
}

// --------------------
// Home Screen
// --------------------
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Home Screen", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.Detail.route)
                }
            ) {
                Text("Go to Detail Page")
            }
        }
    }
}

// --------------------
// Detail Screen
// --------------------
@Composable
fun DetailScreen(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(text = "Detail Screen", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.popBackStack() // go back
                }
            ) {
                Text("Back")
            }
        }
    }
}