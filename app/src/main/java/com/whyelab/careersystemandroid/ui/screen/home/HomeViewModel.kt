package com.whyelab.careersystemandroid.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whyelab.careersystemandroid.data.model.StatResponse
import com.whyelab.careersystemandroid.data.repository.StatRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.*

class HomeViewModel : ViewModel() {

    private val repository = StatRepository()

    var stats by mutableStateOf<StatResponse?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

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