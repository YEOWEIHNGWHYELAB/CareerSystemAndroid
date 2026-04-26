package com.whyelab.careersystemandroid.data.remote.joblist

import com.whyelab.careersystemandroid.data.model.JobList
import retrofit2.http.GET

interface ApiService {
    @GET("joblisting/")
    suspend fun getJobList(): List<JobList>
}