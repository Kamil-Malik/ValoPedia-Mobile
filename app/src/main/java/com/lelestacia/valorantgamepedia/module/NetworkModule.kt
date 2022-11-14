package com.lelestacia.valorantgamepedia.module

import androidx.viewbinding.BuildConfig
import com.lelestacia.valorantgamepedia.data.data_source.api.HendrikDevApi
import com.lelestacia.valorantgamepedia.data.data_source.api.ValorantApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

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
    fun provideHendrikApi(okHttpClient: OkHttpClient): HendrikDevApi =
        Retrofit.Builder().baseUrl(HendrikDevApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(HendrikDevApi::class.java)
}