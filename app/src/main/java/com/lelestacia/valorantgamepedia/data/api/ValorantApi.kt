package com.lelestacia.valorantgamepedia.data.api

import com.lelestacia.valorantgamepedia.data.model.remote.GenericResponse
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.RemoteMapData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents(): GenericResponse<RemoteAgentData>

    @GET("currencies")
    suspend fun getCurrencies(): GenericResponse<NetworkCurrencyData>

    @GET("weapons")
    suspend fun getWeapons(): GenericResponse<NetworkWeaponData>

    @GET("maps")
    suspend fun getMaps(): GenericResponse<RemoteMapData>

    companion object {
        const val BASE_URL = "https://valorant-api.com/v1/"
    }
}