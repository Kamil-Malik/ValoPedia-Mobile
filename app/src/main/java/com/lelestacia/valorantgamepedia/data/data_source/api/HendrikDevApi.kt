package com.lelestacia.valorantgamepedia.data.data_source.api

import com.lelestacia.valorantgamepedia.data.model.remote.GenericResponse
import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews
import retrofit2.http.GET

interface HendrikDevApi {

    @GET("valorant/v1/website/en-us")
    suspend fun getNews(): GenericResponse<NetworkNews>

    companion object {
        const val BASE_URL = "https://api.henrikdev.xyz/"
    }
}