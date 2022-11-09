package com.lelestacia.valorantgamepedia.data.model.local.agent_data

import androidx.room.Embedded
import androidx.room.Relation

data class AgentWithAbility(

    @Embedded val agent: LocalAgentData,

    @Relation(
        parentColumn = "agent_name",
        entityColumn = "agent_name"
    )
    val ability: List<LocalAbility>
)