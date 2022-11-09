package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import kotlinx.coroutines.*

class ConvertAbility {

    suspend fun execute(
        list: List<RemoteAgentData>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<LocalAbility> {
        val arr = arrayListOf<LocalAbility>()
        val job = arrayListOf<Job>()
        list.forEach { agent ->
            agent.abilities.forEach {
                val currentJob = CoroutineScope(coroutineDispatcher).launch {
                    arr.add(
                        LocalAbility(
                            agentName = agent.displayName,
                            displayName = it.displayName,
                            displayIcon = it.displayIcon ?: "",
                            description = it.description,
                            slot = it.slot
                        )
                    )
                }
                job.add(currentJob)
            }
        }
        job.joinAll()
        return arr
    }
}