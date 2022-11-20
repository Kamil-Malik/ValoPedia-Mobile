package com.lelestacia.valorantgamepedia.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lelestacia.valorantgamepedia.data.model.local.news.dao.NewsDao
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News

@Database(
    entities = [News::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}