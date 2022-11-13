package com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Ability
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Agent

data class AgentWithAbility(

    @Embedded val agent: Agent,

    @Relation(
        parentColumn = "uuid",
        entityColumn = "agent_uuid"
    )
    val ability: List<Ability>
)