package com.lelestacia.valorantgamepedia.data.remote

import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.NetworkAgent
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.NetworkMap
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon
import javax.inject.Inject

class ValorantApiSource @Inject constructor(private val apiService: ValorantApi) {

    suspend fun getAgents(): List<NetworkAgent> {
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

    suspend fun getMaps(): List<NetworkMap> {
        return apiService
            .getMaps().data
            .sortedBy { it.displayName }
    }

    suspend fun getWeapons(): List<NetworkWeapon> {
        return apiService
            .getWeapons().data
            .sortedBy { it.displayName }
    }
}