package com.lelestacia.valorantgamepedia.data.local

import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.AgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.CurrenciesData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.MapsData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.WeaponsData

object MemoryCache {

    val agents : MutableList<AgentData> = mutableListOf()

    val currencies : MutableList<CurrenciesData> = mutableListOf()

    val maps : MutableList<MapsData> = mutableListOf()

    val weapons : MutableList<WeaponsData> = mutableListOf()
}