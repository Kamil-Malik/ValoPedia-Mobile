package com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail

import androidx.lifecycle.ViewModel
import com.lelestacia.valorantgamepedia.data.model.local.weapon.relation.WeaponDataWithDamageRangeAndSkin
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailWeaponViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    suspend fun getWeaponDetail(uuid: String) : WeaponDataWithDamageRangeAndSkin {
        return repository.getWeaponDetail(uuid)
    }
}