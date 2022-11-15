package com.lelestacia.valorantgamepedia.data.model.local.news.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class News(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "image") val bannerUrl: String,

    @ColumnInfo(name = "category") val category: String,

    @ColumnInfo(name = "timestamp") val date: String,

    @ColumnInfo(name = "secondary_url") val externalLink: String?,

    @ColumnInfo(name = "title") val title: String,

    @ColumnInfo(name = "url") val url: String
)