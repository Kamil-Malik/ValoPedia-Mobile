package com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData

data class AgentWithAbility(

    @Embedded val agent: LocalAgentData,

    @Relation(
        parentColumn = KEY_CONNECTOR,
        entityColumn = KEY_CONNECTOR
    )
    val ability: List<LocalAbility>
) {
    companion object {
        private const val KEY_CONNECTOR = "agent_uuid"
    }
}