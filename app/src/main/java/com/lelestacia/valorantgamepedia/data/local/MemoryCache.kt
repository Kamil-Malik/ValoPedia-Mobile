package com.lelestacia.valorantgamepedia.data.local

import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.Map
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.NetworkAgent
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.NetworkMap
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon

object MemoryCache {

    val agents: MutableList<NetworkAgent> = mutableListOf()

    val currencies: MutableList<NetworkCurrencyData> = mutableListOf()

    val maps: MutableList<NetworkMap> = mutableListOf()

    val localMaps: MutableList<Map> = mutableListOf()

    val weapons: MutableList<NetworkWeapon> = mutableListOf()
}