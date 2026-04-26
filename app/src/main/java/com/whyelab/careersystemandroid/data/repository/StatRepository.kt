package com.whyelab.careersystemandroid.data.repository

import com.whyelab.careersystemandroid.data.model.StatResponse
import com.whyelab.careersystemandroid.data.remote.RetrofitClient

class StatRepository {

    suspend fun getStats(): StatResponse {
        return RetrofitClient.api.getStats()
    }
}