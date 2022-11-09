package com.lelestacia.valorantgamepedia.data.remote

import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.RemoteMapData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import javax.inject.Inject

class ValorantApiSource @Inject constructor(private val apiService: ValorantApi) {

    suspend fun getAgents(): List<RemoteAgentData> {
        return apiService
            .getAgents().data
            .filter { it.isPlayableCharacter }
            .sortedBy { it.displayName }
    }

    suspend fun getCurrencies(): List<NetworkCurrencyData> {
        return apiService
            .getCurrencies().data
            .sortedBy { it.displayName }
    }

    suspend fun getMaps(): List<RemoteMapData> {
        return apiService
            .getMaps().data
            .sortedBy { it.networkMapDisplayName }
    }

    suspend fun getWeapons(): List<NetworkWeaponData> {
        return apiService
            .getWeapons().data
            .sortedBy { it.displayName }
    }
}