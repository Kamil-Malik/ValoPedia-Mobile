package com.lelestacia.valorantgamepedia.ui.agents.agents_detail

import androidx.lifecycle.ViewModel
import com.lelestacia.valorantgamepedia.data.model.local.agent.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    suspend fun getAgents(uuid: String): AgentWithAbility {
        return repository.getAgentByUUID(uuid)
    }
}