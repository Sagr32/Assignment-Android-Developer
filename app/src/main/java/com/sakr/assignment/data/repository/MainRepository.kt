package com.sakr.assignment.data.repository

import com.sakr.assignment.data.local.User
import com.sakr.assignment.data.local.UsersDao
import com.sakr.assignment.data.models.NewsResponse
import com.sakr.assignment.data.models.SourceResponse
import com.sakr.assignment.data.remote.ApiInterface
import com.sakr.assignment.data.remote.ApiStatus
import com.sakr.assignment.data.remote.safeApiCall
import com.sakr.assignment.utils.Constants.BASE_URL
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val retrofitService: ApiInterface,
    private val usersDao: UsersDao
) {

    suspend fun getHeadlines(country: String): ApiStatus<NewsResponse> =
        safeApiCall { retrofitService.getHeadlines(country) }

    suspend fun getSources(): ApiStatus<SourceResponse> =
        safeApiCall {
            retrofitService.getSources()
        }

    suspend fun insertUser(user: User) {
        usersDao.insertUser(user)
    }

    suspend fun checkUser(email: String, password: String): User? {
        return usersDao.readLoginData(email, password)
    }


}