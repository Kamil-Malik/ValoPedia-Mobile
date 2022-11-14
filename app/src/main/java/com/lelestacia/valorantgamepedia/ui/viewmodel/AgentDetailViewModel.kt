package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.lelestacia.valorantgamepedia.data.model.local.agent.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.repository.contract.ValorantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val repository: ValorantRepository
) : ViewModel() {

    suspend fun getAgents(uuid: String): AgentWithAbility {
        return repository.getAgentByUUID(uuid)
    }
}