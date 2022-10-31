package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.model.remote.AgentData
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAgents() : Flow<FinalResponse<List<AgentData>>>
}