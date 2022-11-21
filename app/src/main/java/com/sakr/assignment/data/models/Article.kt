package com.sakr.assignment.data.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


@Parcelize
data class Article(
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("content")
    val content: String? = "",
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: SourceX,
    @SerializedName("url")
    val url: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("urlToImage")
    val urlToImage: String
) : Parcelable


@Parcelize
data class SourceX(
    @SerializedName("id")
    val id: String? = "", // fox-sports
    @SerializedName("name")
    val name: String = "Unknown Source"// Fox Sports
) : Parcelable