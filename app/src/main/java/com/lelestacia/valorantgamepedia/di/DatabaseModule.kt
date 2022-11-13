package com.lelestacia.valorantgamepedia.di

import android.content.Context
import androidx.room.Room
import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.local.WeaponDatabase
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

    @Provides
    @Singleton
    fun provideWeaponDatabase(@ApplicationContext mContext: Context): WeaponDatabase {
        return Room.databaseBuilder(
            mContext,
            WeaponDatabase::class.java,
            "weapon_db"
        ).build()
    }
}