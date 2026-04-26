package com.whyelab.careersystemandroid.ui.screen.joblist

import com.whyelab.careersystemandroid.data.repository.JobListRepo
import com.whyelab.careersystemandroid.data.model.JobList
import com.whyelab.careersystemandroid.data.local.ConfigManager

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class JobListViewModel(application: Application) : AndroidViewModel(application) {

    private val configManager = ConfigManager(application)
    private val repo = JobListRepo(configManager)

    var jobs by mutableStateOf<List<JobList>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun loadJobs() {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                jobs = repo.getJobList()
            } catch (e: Exception) {
                error = e.message ?: "Unknown error"
            } finally {
                isLoading = false
            }
        }
    }
}