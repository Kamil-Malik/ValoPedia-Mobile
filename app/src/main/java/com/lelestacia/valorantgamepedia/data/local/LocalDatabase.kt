package com.lelestacia.valorantgamepedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.dao.AgentDao
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.converter.StringConverter
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.dao.MapDao

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