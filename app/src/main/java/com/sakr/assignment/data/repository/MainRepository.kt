package com.sakr.assignment.data.repository

import com.sakr.assignment.data.models.NewsResponse
import com.sakr.assignment.data.models.SourceResponse
import com.sakr.assignment.data.remote.ApiInterface
import com.sakr.assignment.data.remote.ApiStatus
import com.sakr.assignment.data.remote.safeApiCall

class MainRepository(private val retrofitService: ApiInterface) {

    suspend fun getHeadlines(country: String): ApiStatus<NewsResponse> =
        safeApiCall { retrofitService.getHeadlines(country) }

    suspend fun getSources(): ApiStatus<SourceResponse> =
        safeApiCall {
            retrofitService.getSources()
        }
}