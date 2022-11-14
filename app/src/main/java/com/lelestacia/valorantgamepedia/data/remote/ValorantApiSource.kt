package com.lelestacia.valorantgamepedia.data.remote

import com.lelestacia.valorantgamepedia.data.data_source.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.model.remote.agent.NetworkAgent
import com.lelestacia.valorantgamepedia.data.model.remote.maps.NetworkMap
import com.lelestacia.valorantgamepedia.data.model.remote.weapon.NetworkWeapon
import javax.inject.Inject

class ValorantApiSource @Inject constructor(private val apiService: ValorantApi) {

    suspend fun getAgents(): List<NetworkAgent> {
        return apiService
            .getAgents().data
            .filter { it.isPlayableCharacter }
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