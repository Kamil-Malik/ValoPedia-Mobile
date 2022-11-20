package com.lelestacia.valorantgamepedia.module

import android.content.Context
import androidx.room.Room
import com.lelestacia.valorantgamepedia.data.data_source.local.NewsDatabase
import com.lelestacia.valorantgamepedia.data.data_source.local.WikipediaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext mContext: Context): WikipediaDatabase {
        return Room
            .databaseBuilder(mContext, WikipediaDatabase::class.java, "main_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext mContext: Context): NewsDatabase {
        return Room
            .databaseBuilder(mContext, NewsDatabase::class.java, "news_db")
            .build()
    }
}