package com.lelestacia.valorantgamepedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.AgentDao
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.converter.StringConverter
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.MapDao

@Database(
    entities = [LocalMapData::class, LocalAgentData::class, LocalAbility::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [StringConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun mapDao(): MapDao

    abstract fun agentDao(): AgentDao
}