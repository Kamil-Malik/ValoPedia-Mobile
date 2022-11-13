package com.lelestacia.valorantgamepedia.module

import android.content.Context
import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.remote.ValorantApiSource
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.data.repository.MainRepositoryImpl
import com.lelestacia.valorantgamepedia.data.repository.SharedPrefRepository
import com.lelestacia.valorantgamepedia.data.repository.SharedPrefRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(valorantApi: ValorantApi): ValorantApiSource =
        ValorantApiSource(valorantApi)

    @Provides
    @Singleton
    fun provideMainRepository(
        valorantApi: ValorantApiSource,
        localDatabase: LocalDatabase
    ): MainRepository =
        MainRepositoryImpl(valorantApi, Dispatchers.IO, localDatabase)

    @Provides
    @Singleton
    fun provideSharedPrefRepository(@ApplicationContext mContext: Context): SharedPrefRepository =
        SharedPrefRepositoryImpl(mContext)
}