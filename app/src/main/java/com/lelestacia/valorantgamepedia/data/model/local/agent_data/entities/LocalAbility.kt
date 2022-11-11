package com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "ability_table", primaryKeys = ["skill_name", "slot"])
data class LocalAbility(

    @ColumnInfo(name = "skill_name") val displayName: String,

    @ColumnInfo(name = "skill_icon") val displayIcon: String,

    @ColumnInfo(name = "skill_description") val description: String,

    @ColumnInfo(name = "slot") val slot: String,

    @ColumnInfo(name = "agent_uuid") val agentUUID: String
)