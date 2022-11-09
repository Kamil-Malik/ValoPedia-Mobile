package com.lelestacia.valorantgamepedia.ui.agents

import androidx.lifecycle.ViewModel
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAgentData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getAgents(): Flow<FinalResponse<List<LocalAgentData>>> {
        return mainRepository.getAgents()
    }
}