package com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "ability_table", primaryKeys = ["name", "slot"])
data class Ability(

    @ColumnInfo(name = "name") val displayName: String,

    @ColumnInfo(name = "icon") val displayIcon: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "slot") val slot: String,

    @ColumnInfo(name = "agent_uuid") val agentUUID: String
)