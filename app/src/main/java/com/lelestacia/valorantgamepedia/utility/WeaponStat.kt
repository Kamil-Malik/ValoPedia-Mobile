package com.lelestacia.valorantgamepedia.utility

import com.lelestacia.valorantgamepedia.data.model.local.WeaponDisplayStat
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.WeaponStats

class WeaponStat {

    fun get(stat: WeaponStats): List<WeaponDisplayStat> {
        val arr = arrayListOf<WeaponDisplayStat>()
        arr.add(
            WeaponDisplayStat(
                title = "Fire Rate",
                value = stat.fireRate.toString(),
                calculation = RDSS
            )
        )
        arr.add(
            WeaponDisplayStat(
                title = "Run Speed",
                value = stat.runSpeedMultiplier.toString(),
                calculation = MSEC
            )
        )
        arr.add(
            WeaponDisplayStat(
                title = "Equip Speed",
                value = stat.equipTimeSeconds.toString(),
                calculation = SEC
            )
        )
        arr.add(
            WeaponDisplayStat(
                title = "1St Shot Spread",
                value = stat.firstBulletAccuracy.toString(),
                calculation = DEG
            )
        )
        arr.add(
            WeaponDisplayStat(
                title = "Reload Speed",
                value = stat.reloadTimeSeconds.toString(),
                calculation = SEC
            )
        )
        arr.add(
            WeaponDisplayStat(
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