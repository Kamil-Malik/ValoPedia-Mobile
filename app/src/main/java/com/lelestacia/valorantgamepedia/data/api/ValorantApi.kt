package com.lelestacia.valorantgamepedia.data.api

import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.AgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.CurrenciesData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.weapons_info.WeaponsData
import com.lelestacia.valorantgamepedia.utility.GenericResponse
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents() : GenericResponse<AgentData>

    @GET("currencies")
    suspend fun getCurrencies() : GenericResponse<CurrenciesData>

    @GET("weapons")
    suspend fun getWeapons() : GenericResponse<WeaponsData>
}