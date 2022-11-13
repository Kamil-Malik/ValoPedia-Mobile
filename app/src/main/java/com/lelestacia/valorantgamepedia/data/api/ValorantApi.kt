package com.lelestacia.valorantgamepedia.data.api

import com.lelestacia.valorantgamepedia.data.model.remote.GenericResponse
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.NetworkAgent
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.NetworkMap
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents(): GenericResponse<NetworkAgent>

    @GET("currencies")
    suspend fun getCurrencies(): GenericResponse<NetworkCurrencyData>

    @GET("weapons")
    suspend fun getWeapons(): GenericResponse<NetworkWeapon>

    @GET("maps")
    suspend fun getMaps(): GenericResponse<NetworkMap>

    companion object {
        const val BASE_URL = "https://valorant-api.com/v1/"
    }
}