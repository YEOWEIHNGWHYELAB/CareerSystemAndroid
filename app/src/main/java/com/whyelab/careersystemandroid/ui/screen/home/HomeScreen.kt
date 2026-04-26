package com.whyelab.careersystemandroid.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {

    val stats = viewModel.stats

    LaunchedEffect(Unit) {
        viewModel.loadStats()
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text("Dashboard", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = {
                    navController.navigate("config")
                }) {
                    Text("Change Server")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        viewModel.loadStats()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1976D2), // blue (you can change this)
                        contentColor = Color.White
                    )
                ) {
                    Text("Refresh")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (viewModel.error != null) {
                Text(
                    text = "Failed to load: $viewModel.error",
                    color = MaterialTheme.colorScheme.error
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    viewModel.loadStats()
                }) {
                    Text("Retry")
                }

            } else if (stats == null) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator()
                }

            } else {
                Text("Companies: ${stats.total_companies}")
                Text("Jobs: ${stats.total_jobs}")
                Text("Applications: ${stats.total_application}")
            }
        }
    }
}