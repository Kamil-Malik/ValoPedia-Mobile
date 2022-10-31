package com.lelestacia.valorantgamepedia.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.remote.AgentData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentsViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getAgents(): LiveData<FinalResponse<List<AgentData>>> {
        return mainRepository.getAgents()
            .asLiveData()
    }
}