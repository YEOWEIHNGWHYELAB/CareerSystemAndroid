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
    private val repository = StatRepository(configManager.getBaseUrl()!!)

    var stats by mutableStateOf<StatResponse?>(null)
        private set

    fun loadStats() {
        viewModelScope.launch {
            stats = repository.getStats()
        }
    }
}