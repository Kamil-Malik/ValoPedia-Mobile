package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Role
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.NetworkAgent

class MapAgent {

    fun fromNetwork(networkAgent: NetworkAgent): Agent {
        return Agent(
            uuid = networkAgent.uuid,
            displayName = networkAgent.displayName,
            displayIcon = networkAgent.displayIcon,
            description = networkAgent.description,
            displayPortrait = networkAgent.fullPortrait ?: "",
            displayTag = networkAgent.characterTags ?: emptyList(),
            role = Role(
                roleID = networkAgent.networkRole?.uuid ?: "",
                displayName = networkAgent.networkRole?.displayName ?: "",
                displayIcon = networkAgent.networkRole?.displayIcon ?: "",
                roleDescription = networkAgent.networkRole?.description ?: ""
            )
        )
    }
}