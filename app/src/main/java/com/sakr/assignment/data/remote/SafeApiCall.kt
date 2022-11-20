package com.sakr.assignment.data.remote

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sakr.assignment.data.models.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


// we'll use this function in all
// repos to handle api errors.
suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): ApiStatus<T> {

    // Returning api response
    // wrapped in Resource class
    return withContext(Dispatchers.IO) {
        try {

            // Here we are calling api lambda
            // function that will return response
            // wrapped in Retrofit's Response class
            val response: Response<T> = apiToBeCalled()

            if (response.isSuccessful) {
                // In case of success response we
                // are returning Resource.Success object
                // by passing our data in it.
                ApiStatus.Success(data = response.body()!!)
            } else {
                val gson = Gson()
                val type = object : TypeToken<ErrorResponse>() {}.type
                val errorResponse: ErrorResponse? =
                    gson.fromJson(response.errorBody()!!.charStream(), type)
                ApiStatus.Error(
                    errorMessage =
//                    errorResponse?.failureMessage
//                        ?:
                    errorResponse?.message ?: "Something went wrong"
                )
            }

        } catch (e: HttpException) {
            // Returning HttpException's message
            // wrapped in Resource.Error
            ApiStatus.Error(errorMessage = e.message ?: "Something went wrong")
        } catch (e: IOException) {
            // Returning no internet message
            // wrapped in Resource.Error
            ApiStatus.Error("Please check your network connection")
        } catch (e: Exception) {
            // Returning 'Something went wrong' in case
            // of unknown error wrapped in Resource.Error
            ApiStatus.Error(errorMessage = "Something went wrong")
        }
    }
}