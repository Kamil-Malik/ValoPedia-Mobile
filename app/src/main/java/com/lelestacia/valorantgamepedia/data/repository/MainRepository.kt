package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAgents(): Flow<FinalResponse<List<RemoteAgentData>>>

    fun getCurrencies(): Flow<FinalResponse<List<NetworkCurrencyData>>>

    fun getMaps(): Flow<FinalResponse<List<LocalMapData>>>

    fun getWeapons(): Flow<FinalResponse<List<NetworkWeaponData>>>
}