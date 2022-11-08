package com.lelestacia.valorantgamepedia.di

import android.content.Context
import androidx.room.Room
import androidx.viewbinding.BuildConfig
import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        } else {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideValorantApi(okHttpClient: OkHttpClient): ValorantApi =
        Retrofit.Builder().baseUrl(ValorantApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ValorantApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext mContext: Context): LocalDatabase {
        return Room.databaseBuilder(
            mContext,
            LocalDatabase::class.java,
            "valopedia_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        valorantApi: ValorantApi,
        localDatabase: LocalDatabase
    ): MainRepository =
        MainRepositoryImpl(valorantApi, Dispatchers.IO, localDatabase)

    @Provides
    @Singleton
    fun provideSharedPrefRepository(@ApplicationContext mContext: Context): SharedPrefRepository =
        SharedPrefRepositoryImpl(mContext)
}