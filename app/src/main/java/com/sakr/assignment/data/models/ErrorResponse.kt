package com.sakr.assignment.data.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Parcelize
data class ErrorResponse(
    @SerializedName("code")
    val code: String, // apiKeyInvalid
    @SerializedName("message")
    val message: String, // Your API key is invalid or incorrect. Check your key, or go to https://newsapi.org to create a free API key.
    @SerializedName("status")
    val status: String // error
) : Parcelable