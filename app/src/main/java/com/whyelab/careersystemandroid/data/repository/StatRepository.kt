package com.whyelab.careersystemandroid.data.repository

import com.whyelab.careersystemandroid.data.remote.RetrofitClient

class StatRepository(private val baseUrl: String) {

    private val api = RetrofitClient.getInstance(baseUrl)

    suspend fun getStats() = api.getStats()
}