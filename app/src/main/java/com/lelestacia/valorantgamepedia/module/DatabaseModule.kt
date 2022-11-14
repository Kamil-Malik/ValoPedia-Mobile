package com.lelestacia.valorantgamepedia.module

import android.content.Context
import androidx.room.Room
import com.lelestacia.valorantgamepedia.data.data_source.local.LocalDatabase
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
    fun provideLocalDatabase(@ApplicationContext mContext: Context): LocalDatabase {
        return Room.databaseBuilder(
            mContext,
            LocalDatabase::class.java,
            "valopedia_db"
        ).build()
    }
}