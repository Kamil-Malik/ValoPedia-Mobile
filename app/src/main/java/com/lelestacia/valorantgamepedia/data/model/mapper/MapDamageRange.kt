package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon
import kotlinx.coroutines.*

class MapDamageRange {

    suspend fun fromNetwork(
        networkWeapon: NetworkWeapon,
        coroutineDispatcher: CoroutineDispatcher
    ): List<DamageRange> {
        val arr = arrayListOf<Deferred<DamageRange>>()
        networkWeapon.networkWeaponStatistic?.networkDamageRanges?.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    DamageRange(
                        range = "${it.rangeStartMeters} - ${it.rangeEndMeters}",
                        headDamage = it.headDamage.toInt(),
                        bodyDamage = it.bodyDamage,
                        legDamage = it.legDamage.toInt(),
                        weaponUUID = networkWeapon.uuid
                    )
                }
            )
        }
        return arr.awaitAll()
    }
}