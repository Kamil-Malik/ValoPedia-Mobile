package com.lelestacia.valorantgamepedia.usecases

import com.lelestacia.valorantgamepedia.data.model.local.WeaponDamageStat
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.DamageRange

class WeaponDamageRange {

    fun get(rawData: List<DamageRange>): List<WeaponDamageStat> {
        val arr = arrayListOf<WeaponDamageStat>()
        arr.add(
            WeaponDamageStat(
                range = "Damage",
                headDamage = "Head",
                bodyDamage = "Body",
                legDamage = "Leg"
            )
        )

        rawData.forEach {
            arr.add(
                WeaponDamageStat(
                    range = "${it.rangeStartMeters} - ${it.rangeEndMeters}m",
                    headDamage = String.format("%.2f", it.headDamage.toFloat()),
                    bodyDamage = String.format("%.2f", it.bodyDamage.toFloat()),
                    legDamage = String.format("%.2f", it.legDamage.toFloat())
                )
            )
        }
        return arr
    }
}