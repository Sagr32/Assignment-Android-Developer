package com.sakr.assignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sakr.assignment.data.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticles(articles: List<Article>)

    @Query("SELECT * FROM article_table")
    suspend fun getSavedArticles(): List<Article>

    @Query("DELETE FROM article_table WHERE url =:articleUrl")
    suspend fun deleteArticle(articleUrl: String)


}