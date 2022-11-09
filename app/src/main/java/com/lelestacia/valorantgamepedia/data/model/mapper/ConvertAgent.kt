package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalRole
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Role

class ConvertAgent {

    fun execute(remoteAgentData: List<RemoteAgentData>): List<LocalAgentData> {
        val arr = arrayListOf<LocalAgentData>()
        remoteAgentData.forEach {
            arr.add(mapAgent(it))
        }
        return arr
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