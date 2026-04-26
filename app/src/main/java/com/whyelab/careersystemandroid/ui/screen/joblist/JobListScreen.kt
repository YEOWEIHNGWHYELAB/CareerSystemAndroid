package com.whyelab.careersystemandroid.ui.screen.joblist

import com.whyelab.careersystemandroid.data.model.JobList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun JobListScreen(
    navController: NavHostController,
    viewModel: JobListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.loadJobs()
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {

            Text(
                text = "Job Listings",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Back")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(onClick = { viewModel.loadJobs() }) {
                    Text("Refresh")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator()
                }

                viewModel.error != null -> {
                    Text(
                        text = "Error: ${viewModel.error}",
                        color = MaterialTheme.colorScheme.error
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(onClick = { viewModel.loadJobs() }) {
                        Text("Retry")
                    }
                }

                else -> {
                    LazyColumn {
                        items(viewModel.jobs) { job ->
                            JobItem(job)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun JobItem(job: JobList) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Text(job.title, style = MaterialTheme.typography.titleMedium)
            Text(job.company_name, style = MaterialTheme.typography.bodyMedium)
            Text(job.location, style = MaterialTheme.typography.bodySmall)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Open Link",
                color = Color(0xFF1976D2),
                modifier = Modifier.clickable {
                    // optional: open browser
                }
            )
        }
    }
}