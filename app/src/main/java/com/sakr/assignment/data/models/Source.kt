package com.sakr.assignment.data.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Parcelize
data class Source(
    @SerializedName("category")
    val category: String, // general
    @SerializedName("country")
    val country: String, // us
    @SerializedName("description")
    val description: String, // Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.
    @SerializedName("id")
    val id: String, // abc-news
    @SerializedName("language")
    val language: String, // en
    @SerializedName("name")
    val name: String, // ABC News
    @SerializedName("url")
    val url: String // https://abcnews.go.com
) : Parcelable