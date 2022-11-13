package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponStatistic
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon

class MapStatistic {

    fun fromNetwork(networkWeapon: NetworkWeapon): WeaponStatistic? {
        if (networkWeapon.networkWeaponStatistic == null)
            return null
        return WeaponStatistic(
            wallPenetration = networkWeapon.networkWeaponStatistic.wallPenetration,
            shotgunPelletCount = networkWeapon.networkWeaponStatistic.shotgunPelletCount,
            runSpeedMultiplier = networkWeapon.networkWeaponStatistic.runSpeedMultiplier,
            reloadTimeSeconds = networkWeapon.networkWeaponStatistic.reloadTimeSeconds,
            magazineSize = networkWeapon.networkWeaponStatistic.magazineSize,
            firstBulletAccuracy = networkWeapon.networkWeaponStatistic.firstBulletAccuracy,
            fireRate = networkWeapon.networkWeaponStatistic.fireRate,
            fireMode = networkWeapon.networkWeaponStatistic.fireMode ?: "",
            feature = networkWeapon.networkWeaponStatistic.feature ?: "",
            equipTimeSeconds = networkWeapon.networkWeaponStatistic.equipTimeSeconds,
            weaponUUID = networkWeapon.uuid
        )
    }
}