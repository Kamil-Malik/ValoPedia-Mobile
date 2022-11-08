package com.lelestacia.valorantgamepedia.data.model.local.agent_data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "agent_table")
data class LocalAgentData(

    @PrimaryKey @ColumnInfo(name = "uuid") val localAgentUUID: String,

    @ColumnInfo(name = "agent_name") val localAgentName: String,

    @ColumnInfo(name = "agent_icon") val localAgentIcon: String,

    @ColumnInfo(name = "full_portrait") val localAgentPortrait: String,

    @ColumnInfo(name = "agent_description") val localAgentDescription: String,

//    @Embedded val localAgentAbilities: List<LocalAbility>,

    @ColumnInfo(name = "character_tag") val localAgentTags: List<String>,

    @Embedded val localAgentRole: LocalRole
) : Parcelable
