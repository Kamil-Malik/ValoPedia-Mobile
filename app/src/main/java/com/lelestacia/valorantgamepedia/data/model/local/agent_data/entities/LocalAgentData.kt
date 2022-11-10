package com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agent_table")
data class LocalAgentData(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "agent_uuid") val uuid: String,

    @ColumnInfo(name = "agent_name") val displayName: String,

    @ColumnInfo(name = "agent_icon") val displayIcon: String,

    @ColumnInfo(name = "agent_portrait") val displayPortrait: String,

    @ColumnInfo(name = "agent_description") val description: String,

    @ColumnInfo(name = "agent_tag") val displayTag: List<String>,

    @Embedded val role: LocalRole
)
