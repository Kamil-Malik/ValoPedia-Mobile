package com.lelestacia.valorantgamepedia.usecases

import com.lelestacia.valorantgamepedia.data.model.local.weapon.utility.DisplayWeaponStat
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponStatistic

class DisplayStatistic {

    fun get(stat: WeaponStatistic): List<DisplayWeaponStat> {
        val arr = arrayListOf<DisplayWeaponStat>()
        arr.add(
            DisplayWeaponStat(
                title = "Fire Rate",
                value = stat.fireRate.toString(),
                calculation = RDSS
            )
        )
        arr.add(
            DisplayWeaponStat(
                title = "Run Speed",
                value = stat.runSpeedMultiplier.toString(),
                calculation = MSEC
            )
        )
        arr.add(
            DisplayWeaponStat(
                title = "Equip Speed",
                value = stat.equipTimeSeconds.toString(),
                calculation = SEC
            )
        )
        arr.add(
            DisplayWeaponStat(
                title = "1St Shot Spread",
                value = stat.firstBulletAccuracy.toString(),
                calculation = DEG
            )
        )
        arr.add(
            DisplayWeaponStat(
                title = "Reload Speed",
                value = stat.reloadTimeSeconds.toString(),
                calculation = SEC
            )
        )
        arr.add(
            DisplayWeaponStat(
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