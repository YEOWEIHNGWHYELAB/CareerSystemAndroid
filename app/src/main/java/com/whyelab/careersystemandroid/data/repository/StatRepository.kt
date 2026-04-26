package com.whyelab.careersystemandroid.data.repository

import com.whyelab.careersystemandroid.data.local.ConfigManager
import com.whyelab.careersystemandroid.data.remote.RetrofitClient

class StatRepository(configManager: ConfigManager) {

    private val api = RetrofitClient.getInstance(configManager.getBaseUrl()!!)

    suspend fun getStats() = api.getStats()
}