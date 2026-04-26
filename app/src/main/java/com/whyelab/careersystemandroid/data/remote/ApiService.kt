package com.whyelab.careersystemandroid.data.remote

import com.whyelab.careersystemandroid.data.model.StatResponse
import retrofit2.http.GET

interface ApiService {
    @GET("stat/")
    suspend fun getStats(): StatResponse
}