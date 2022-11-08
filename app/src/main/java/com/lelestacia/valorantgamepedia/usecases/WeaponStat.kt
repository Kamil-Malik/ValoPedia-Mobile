package com.lelestacia.valorantgamepedia.usecases

import com.lelestacia.valorantgamepedia.data.model.local.LocalWeaponStat
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponStat

class WeaponStat {

    fun get(stat: NetworkWeaponStat): List<LocalWeaponStat> {
        val arr = arrayListOf<LocalWeaponStat>()
        arr.add(
            LocalWeaponStat(
                title = "Fire Rate",
                value = stat.fireRate.toString(),
                calculation = RDSS
            )
        )
        arr.add(
            LocalWeaponStat(
                title = "Run Speed",
                value = stat.runSpeedMultiplier.toString(),
                calculation = MSEC
            )
        )
        arr.add(
            LocalWeaponStat(
                title = "Equip Speed",
                value = stat.equipTimeSeconds.toString(),
                calculation = SEC
            )
        )
        arr.add(
            LocalWeaponStat(
                title = "1St Shot Spread",
                value = stat.firstBulletAccuracy.toString(),
                calculation = DEG
            )
        )
        arr.add(
            LocalWeaponStat(
                title = "Reload Speed",
                value = stat.reloadTimeSeconds.toString(),
                calculation = SEC
            )
        )
        arr.add(
            LocalWeaponStat(
                title = "Magazine",
                value = stat.magazineSize.toString(),
                calculation = RDS
            )
        )
        return arr
    }

    companion object {
        private const val RDSS = "RDS/SEC"
        private const val MSEC = "M/SEC"
        private const val SEC = "SEC"
        private const val DEG = "DEG [HIP/ADS]"
        private const val RDS = "RDS"
    }
}