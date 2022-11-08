package com.lelestacia.valorantgamepedia.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getAgents(): LiveData<FinalResponse<List<RemoteAgentData>>> {
        return mainRepository.getAgents()
            .asLiveData()
    }
}