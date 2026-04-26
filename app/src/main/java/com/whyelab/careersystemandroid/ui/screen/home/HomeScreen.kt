package com.whyelab.careersystemandroid.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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

            if (stats == null) {
                Text("Loading...")
            } else {
                Text("Companies: ${stats.total_companies}")
                Text("Jobs: ${stats.total_jobs}")
                Text("Applications: ${stats.total_application}")
            }
        }
    }
}