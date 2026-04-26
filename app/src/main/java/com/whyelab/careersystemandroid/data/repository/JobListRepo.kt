package com.whyelab.careersystemandroid.data.repository

import com.whyelab.careersystemandroid.data.local.ConfigManager
import com.whyelab.careersystemandroid.data.remote.joblist.RetrofitClient

class JobListRepo(configManager: ConfigManager) {

    private val api = RetrofitClient.getInstance(configManager.getBaseUrl()!!)

    suspend fun getJobList() = api.getJobList()
}