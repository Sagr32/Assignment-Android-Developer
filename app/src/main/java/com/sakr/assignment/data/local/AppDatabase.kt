package com.sakr.assignment.data.local

import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sakr.assignment.data.models.Article

@Database(entities = [User::class, Article::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun articleDao(): ArticleDao

}