package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Role
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.RemoteMapData
import kotlinx.coroutines.*

class NetworkToLocal {

    suspend fun convertAgentResponse(
        agent: List<RemoteAgentData>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<LocalAgentData> {
        val arr = arrayListOf<Deferred<LocalAgentData>>()
        agent.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    converAgent(it)
                }
            )
        }
        return arr.awaitAll()
    }

    suspend fun convertMapResponse(
        map: List<RemoteMapData>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<LocalMapData> {
        val arr = arrayListOf<Deferred<LocalMapData>>()
        map.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    convertMap(it)
                }
            )
        }
        return arr.awaitAll()
    }

    private fun converAgent(agent: RemoteAgentData): LocalAgentData {
        val arr = arrayListOf<LocalAbility>()
        agent.abilities.forEach { arr.add(MapAgent().getAbility(it)) }
        return LocalAgentData(
            localAgentUUID = agent.uuid,
            localAgentName = agent.displayName,
            localAgentIcon = agent.displayIcon,
            localAgentPortrait = agent.fullPortrait ?: "",
            localAgentDescription = agent.description,
//            locala = arr,
            localAgentTags = agent.characterTags ?: emptyList(),
            localAgentRole = MapAgent().getRole(agent.role as Role)
        )
    }

    private fun convertMap(map: RemoteMapData): LocalMapData {
        return LocalMapData(
            localMapUUID = map.networkMapUUID,
            localMapDisplayName = map.networkMapDisplayName,
            localMapListIcon = map.networkMapListIcon,
            localMapCoordinate = map.networkMapCoordinate,
            localMapDisplayIcon = map.networkMapDisplayIcon ?: "",
            localMapSplashIcon = map.networkMapSplash
        )
    }
}