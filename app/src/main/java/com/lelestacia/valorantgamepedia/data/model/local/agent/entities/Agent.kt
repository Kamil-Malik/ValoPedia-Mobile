package com.lelestacia.valorantgamepedia.data.model.local.agent.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agent_table")
data class Agent(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "uuid") val uuid: String,

    @ColumnInfo(name = "name") val displayName: String,

    @ColumnInfo(name = "icon") val displayIcon: String,

    @ColumnInfo(name = "portrait") val displayPortrait: String,

    @ColumnInfo(name = "description") val description: String,

    @ColumnInfo(name = "tag") val displayTag: List<String>,

    @Embedded val role: Role
)
