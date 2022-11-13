package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Ability
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.NetworkAgent
import kotlinx.coroutines.*

class MapAbility {

    suspend fun fromNetwork(
        networkAgent: NetworkAgent,
        coroutineDispatcher: CoroutineDispatcher
    ): List<Ability> {
        val arr = arrayListOf<Deferred<Ability>>()
        networkAgent.abilities.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    Ability(
                        displayName = it.displayName,
                        displayIcon = it.displayIcon ?: "",
                        description = it.description,
                        slot = it.slot,
                        agentUUID = networkAgent.uuid
                    )
                }
            )
        }
        return arr.awaitAll()
    }
}