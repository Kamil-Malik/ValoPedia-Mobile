package com.lelestacia.valorantgamepedia.data.local

import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.RemoteMapData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData

object MemoryCache {

    val agents: MutableList<RemoteAgentData> = mutableListOf()

    val currencies: MutableList<NetworkCurrencyData> = mutableListOf()

    val maps: MutableList<RemoteMapData> = mutableListOf()

    val weapons: MutableList<NetworkWeaponData> = mutableListOf()
}