package com.whyelab.careersystemandroid.ui.screen.home

import com.whyelab.careersystemandroid.data.local.ConfigManager
import com.whyelab.careersystemandroid.data.model.StatResponse
import com.whyelab.careersystemandroid.data.repository.StatRepository

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val configManager = ConfigManager(application)
    private val baseUrl = configManager.getBaseUrl() ?: throw IllegalStateException("Base URL not configured")
    private val repository = StatRepository(baseUrl)

    var stats by mutableStateOf<StatResponse?>(null)
        private set
    var isLoading by mutableStateOf(false)
    var error by mutableStateOf<String?>(null)

    fun loadStats() {
        viewModelScope.launch {
            isLoading = true
            error = null

            try {
                stats = repository.getStats()
            } catch (e: Exception) {
                error = e.message
            }

            isLoading = false
        }
    }
}