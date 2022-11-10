package com.lelestacia.valorantgamepedia.data.local

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.RemoteMapData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData

object MemoryCache {

    val agents: MutableList<RemoteAgentData> = mutableListOf()

    val localAgent: MutableList<LocalAgentData> = mutableListOf()

    val currencies: MutableList<NetworkCurrencyData> = mutableListOf()

    val maps: MutableList<RemoteMapData> = mutableListOf()

    val localMaps: MutableList<LocalMapData> = mutableListOf()

    val weapons: MutableList<NetworkWeaponData> = mutableListOf()
}