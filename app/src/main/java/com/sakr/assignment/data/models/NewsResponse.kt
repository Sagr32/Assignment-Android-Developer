package com.sakr.assignment.data.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Parcelize
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String, // ok
    @SerializedName("totalResults")
    val totalResults: Int // 38
) : Parcelable