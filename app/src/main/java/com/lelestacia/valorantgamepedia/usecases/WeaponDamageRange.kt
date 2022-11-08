package com.lelestacia.valorantgamepedia.usecases

import com.lelestacia.valorantgamepedia.data.model.local.LocalWeaponDamageRange
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.DamageRange

class WeaponDamageRange {

    fun get(rawData: List<DamageRange>): List<LocalWeaponDamageRange> {
        val arr = arrayListOf<LocalWeaponDamageRange>()
        arr.add(
            LocalWeaponDamageRange(
                range = "Damage",
                headDamage = "Head",
                bodyDamage = "Body",
                legDamage = "Leg"
            )
        )

        rawData.forEach {
            arr.add(
                LocalWeaponDamageRange(
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