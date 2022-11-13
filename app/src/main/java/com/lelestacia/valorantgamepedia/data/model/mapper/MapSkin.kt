package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon
import kotlinx.coroutines.*

class MapSkin {

    suspend fun fromNetwork(
        networkWeapon: NetworkWeapon,
        coroutineDispatcher: CoroutineDispatcher
    ): List<WeaponSkin> {
        val arr = arrayListOf<Deferred<WeaponSkin>>()
        networkWeapon.networkSkins.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    WeaponSkin(
                        uuid = it.uuid,
                        displayName = it.displayName,
                        displayIcon =
                        if (it.displayName.contains("Standard")) {
                            networkWeapon.displayIcon
                        } else {
                            it.displayIcon ?: ""
                        },
                        weaponUUID = networkWeapon.uuid
                    )
                }
            )
        }
        return arr.awaitAll()
    }
}