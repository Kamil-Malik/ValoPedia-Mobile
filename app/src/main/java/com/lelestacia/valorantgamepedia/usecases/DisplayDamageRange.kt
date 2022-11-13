package com.lelestacia.valorantgamepedia.usecases

import com.lelestacia.valorantgamepedia.data.model.local.DisplayWeaponRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.DamageRange

class DisplayDamageRange {

    fun get(damageRange: List<DamageRange>): List<DisplayWeaponRange> {
        val arr = arrayListOf<DisplayWeaponRange>()
        arr.add(
            DisplayWeaponRange(
                range = "Damage", headDamage = "Head", bodyDamage = "Body", legDamage = "Leg"
            )
        )

        damageRange.forEach {
            arr.add(
                DisplayWeaponRange(
                    range = it.range,
                    headDamage = it.headDamage.toString(),
                    bodyDamage = it.bodyDamage.toString(),
                    legDamage = it.legDamage.toString()
                )
            )
        }
        return arr
    }
}