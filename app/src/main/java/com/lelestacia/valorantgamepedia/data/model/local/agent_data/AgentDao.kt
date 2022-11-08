package com.lelestacia.valorantgamepedia.data.model.local.agent_data

import androidx.room.*

@Dao
interface AgentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(agent: LocalAgentData)

    @Update
    suspend fun update(agent: LocalAgentData)

    @Query("SELECT * FROM agent_table ORDER BY agent_name")
    fun getAgent(): List<LocalAgentData>
}