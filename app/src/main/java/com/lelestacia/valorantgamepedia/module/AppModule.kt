package com.lelestacia.valorantgamepedia.module

import android.content.Context
import com.lelestacia.valorantgamepedia.data.data_source.api.HendrikDevApi
import com.lelestacia.valorantgamepedia.data.data_source.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.data_source.local.NewsDatabase
import com.lelestacia.valorantgamepedia.data.data_source.local.WikipediaDatabase
import com.lelestacia.valorantgamepedia.data.remote.ValorantApiSource
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import com.lelestacia.valorantgamepedia.data.repository.contract.SharedPrefRepository
import com.lelestacia.valorantgamepedia.data.repository.contract.ValorantRepository
import com.lelestacia.valorantgamepedia.data.repository.implementation.HendrikDevRepositoryImpl
import com.lelestacia.valorantgamepedia.data.repository.implementation.SharedPrefRepositoryImpl
import com.lelestacia.valorantgamepedia.data.repository.implementation.ValorantRepositoryImpl
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
        wikipediaDatabase: WikipediaDatabase
    ): ValorantRepository =
        ValorantRepositoryImpl(valorantApi, Dispatchers.IO, wikipediaDatabase)

    @Provides
    @Singleton
    fun provideSharedPrefRepository(@ApplicationContext mContext: Context): SharedPrefRepository =
        SharedPrefRepositoryImpl(mContext)

    @Provides
    @Singleton
    fun provideHendrikDevRepository(apiService: HendrikDevApi, newsDatabase: NewsDatabase) : HendrikDevRepository =
        HendrikDevRepositoryImpl(apiService, newsDatabase, Dispatchers.IO)
}