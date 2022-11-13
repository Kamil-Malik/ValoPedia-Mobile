package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.Map
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.relation.WeaponDataWithDamageRangeAndSkin
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAgents(): Flow<FinalResponse<List<Agent>>>

    suspend fun getAgentByUUID(uuid: String): AgentWithAbility

    fun getCurrencies(): Flow<FinalResponse<List<NetworkCurrencyData>>>

    fun getMaps(): Flow<FinalResponse<List<Map>>>

    fun getWeapons(): Flow<FinalResponse<List<Weapon>>>

    suspend fun getWeaponDetail(uuid: String) : WeaponDataWithDamageRangeAndSkin
}