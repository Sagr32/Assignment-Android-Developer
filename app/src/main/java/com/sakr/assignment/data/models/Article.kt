package com.sakr.assignment.data.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sakr.assignment.utils.Constants.ARTICLE_TABLE


@Parcelize
@Entity(tableName = ARTICLE_TABLE)
data class Article @JvmOverloads constructor(
    @SerializedName("author")
    @ColumnInfo(name = "author")
    val author: String? = "",

    @ColumnInfo(name = "content")
    @SerializedName("content")
    val content: String? = "",

    @ColumnInfo(name = "description")
    @SerializedName("description")
    val description: String,

    @ColumnInfo(name = "publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String,

    @Ignore
    @SerializedName("source")
    val source: SourceX,

    @PrimaryKey()
    @SerializedName("url")
    val url: String,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "urlToImage")
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