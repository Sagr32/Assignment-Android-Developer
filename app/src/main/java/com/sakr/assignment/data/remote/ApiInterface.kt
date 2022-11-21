package com.sakr.assignment.data.remote

import com.sakr.assignment.data.models.NewsResponse
import com.sakr.assignment.data.models.SourceResponse
import com.sakr.assignment.utils.Constants.API_KEY
import com.sakr.assignment.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // top-headlines endpoint
    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int = 20
    ): Response<NewsResponse>

    // sources endpoint
    @GET("/v2/top-headlines/sources")
    suspend fun getSources(
        @Query("apiKey") apiKey: String = API_KEY,
    ): Response<SourceResponse>


}