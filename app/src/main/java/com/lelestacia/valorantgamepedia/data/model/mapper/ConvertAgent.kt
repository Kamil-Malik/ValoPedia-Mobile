package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalRole
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Role
import kotlinx.coroutines.*

class ConvertAgent {

    suspend fun execute(
        remoteAgentData: List<RemoteAgentData>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<LocalAgentData> {
        val arr = arrayListOf<Deferred<LocalAgentData>>()
        remoteAgentData.forEach {
            arr.add(CoroutineScope(coroutineDispatcher).async {
                mapAgent(it)
            })
        }
        return arr.awaitAll()
    }

    private fun mapAgent(remoteAgentData: RemoteAgentData): LocalAgentData {
        with(remoteAgentData) {
            role as Role
            return LocalAgentData(
                uuid = uuid,
                displayName = displayName,
                displayIcon = displayIcon,
                displayPortrait = fullPortrait as String,
                description = description,
                displayTag = characterTags ?: emptyList(),
                role = LocalRole(
                    uuid = role.uuid,
                    displayName = role.displayName,
                    displayIcon = role.displayIcon,
                    description = role.description
                )
            )
        }
    }
}