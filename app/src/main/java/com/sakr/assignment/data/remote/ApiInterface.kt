package com.sakr.assignment.data.remote

import com.sakr.assignment.data.models.NewsResponse
import com.sakr.assignment.data.models.SourceResponse
import com.sakr.assignment.utils.Constants.BASE_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    // top-headlines endpoint
    @GET("/v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String = BASE_URL,
        @Query("pageSize") pageSize: Int = 10
    ): Response<NewsResponse>

    // sources endpoint
    @GET("/v2/top-headlines/sources")
    suspend fun search(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = BASE_URL,
    ): Response<SourceResponse>


}