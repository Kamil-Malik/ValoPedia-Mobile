package com.lelestacia.valorantgamepedia.data.model.local.news.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertListOfNews(news: List<News>)

    @Query("SELECT * FROM news_table ORDER BY timestamp DESC")
    fun getAllNews(): List<News>

    @Query("SELECT * FROM news_table ORDER BY timestamp DESC")
    fun getPagingNews() : PagingSource<Int, News>
}