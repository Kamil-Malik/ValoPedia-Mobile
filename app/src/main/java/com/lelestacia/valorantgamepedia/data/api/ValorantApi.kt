package com.lelestacia.valorantgamepedia.data.api

import com.lelestacia.valorantgamepedia.data.model.remote.AgentData
import com.lelestacia.valorantgamepedia.utility.GenericResponse
import retrofit2.http.GET

interface ValorantApi {

    @GET("agents")
    suspend fun getAgents() : GenericResponse<AgentData>
}