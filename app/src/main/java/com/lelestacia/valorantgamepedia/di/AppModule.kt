package com.lelestacia.valorantgamepedia.di

import androidx.viewbinding.BuildConfig
import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.data.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        Retrofit.Builder().baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(ValorantApi::class.java)

    @Provides
    @Singleton
    fun provideMainRepository(valorantApi: ValorantApi) : MainRepository =
        MainRepositoryImpl(valorantApi, Dispatchers.IO)
}