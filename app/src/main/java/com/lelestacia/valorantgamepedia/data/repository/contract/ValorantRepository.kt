package com.lelestacia.valorantgamepedia.data.repository.contract

import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon.relation.WeaponDataWithDamageRangeAndSkin
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.flow.Flow

interface ValorantRepository {

    fun getAgents(): Flow<FinalResponse<List<Agent>>>

    suspend fun getAgentByUUID(uuid: String): AgentWithAbility

    fun getMaps(): Flow<FinalResponse<List<Map>>>

    fun getWeapons(): Flow<FinalResponse<List<Weapon>>>

    suspend fun getWeaponDetail(uuid: String) : WeaponDataWithDamageRangeAndSkin
}