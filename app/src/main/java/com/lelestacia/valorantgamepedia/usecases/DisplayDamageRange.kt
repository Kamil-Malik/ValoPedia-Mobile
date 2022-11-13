package com.lelestacia.valorantgamepedia.usecases

import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.DamageRange

class DisplayDamageRange {

    fun get(damageRange: List<DamageRange>): List<DamageRange> {
        val arr = arrayListOf<DamageRange>()
        arr.add(
            DamageRange(
                range = "Damage",
                headDamage = "Head",
                bodyDamage = "Body",
                legDamage = "Leg",
                weaponUUID = damageRange[0].weaponUUID
            )
        )
        arr.addAll(damageRange)
        return arr
    }
}