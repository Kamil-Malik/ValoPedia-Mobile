package com.lelestacia.valorantgamepedia.data.model.local.agent_data

import androidx.room.*

@Dao
interface AgentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgent(agent: LocalAgentData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(ability: LocalAbility)

    @Query("SELECT * FROM agent_table")
    fun getListOfAgents(): List<LocalAgentData>

//    @Transaction
//    @Query("SELECT 1 FROM agent_table WHERE agent_name = :agentName")
//    fun getAgentDetail(agentName: String): AgentWithAbility
}