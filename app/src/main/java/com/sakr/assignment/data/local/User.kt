package com.sakr.assignment.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sakr.assignment.utils.Constants

@Entity(tableName = Constants.USER_TABLE)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "password")
    var password: String? = "",
    @ColumnInfo(name = "firstName")
    var firstName: String? = "",
    @ColumnInfo(name = "email")
    var email: String? = "",
)