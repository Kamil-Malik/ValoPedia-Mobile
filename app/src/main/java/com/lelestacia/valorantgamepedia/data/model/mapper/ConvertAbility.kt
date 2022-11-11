package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import kotlinx.coroutines.*

class ConvertAbility {

    suspend fun execute(
        list: List<RemoteAgentData>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<LocalAbility> {
        val arr = arrayListOf<Deferred<LocalAbility>>()
        list.forEach { agent ->
            agent.abilities.forEach {
                arr.add(
                    CoroutineScope(coroutineDispatcher).async {
                        LocalAbility(
                            agentUUID = agent.uuid,
                            displayName = it.displayName,
                            displayIcon = it.displayIcon ?: "",
                            description = it.description,
                            slot = it.slot
                        )
                    }
                )
            }
        }
        return arr.awaitAll()
    }
}