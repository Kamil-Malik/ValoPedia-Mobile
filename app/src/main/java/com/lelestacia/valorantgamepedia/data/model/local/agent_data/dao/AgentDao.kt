package com.lelestacia.valorantgamepedia.data.model.local.agent_data.dao

import androidx.room.*
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation.AgentWithAbility

@Dao
interface AgentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgent(agent: LocalAgentData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbility(ability: LocalAbility)

    @Query("SELECT * FROM agent_table")
    fun getListOfAgents(): List<LocalAgentData>

    @Transaction
    @Query("SELECT * FROM agent_table WHERE agent_uuid = :uuid")
    fun getAgentDetail(uuid: String): AgentWithAbility
}