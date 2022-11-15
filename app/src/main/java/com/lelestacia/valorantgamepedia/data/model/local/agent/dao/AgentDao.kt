package com.lelestacia.valorantgamepedia.data.model.local.agent.dao

import androidx.room.*
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Ability
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent.relation.AgentWithAbility

@Dao
interface AgentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfAgent(agent: List<Agent>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfAbility(ability: List<Ability>)

    @Query("SELECT * FROM agent_table ORDER BY name")
    fun getListOfAgents(): List<Agent>

    @Transaction
    @Query("SELECT * FROM agent_table WHERE uuid = :uuid")
    fun getAgentDetail(uuid: String): AgentWithAbility
}