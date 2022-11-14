package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.data.repository.contract.ValorantRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val repository: ValorantRepository
) : ViewModel() {

    fun getWeapons() : Flow<FinalResponse<List<Weapon>>> {
        return repository.getWeapons()
    }

}