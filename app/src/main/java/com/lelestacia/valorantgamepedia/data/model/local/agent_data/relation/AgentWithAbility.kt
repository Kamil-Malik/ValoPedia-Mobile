package com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData

data class AgentWithAbility(

    @Embedded val agent: LocalAgentData,

    @Relation(
        parentColumn = "agent_name",
        entityColumn = "agent_name"
    )
    val ability: List<LocalAbility>
)