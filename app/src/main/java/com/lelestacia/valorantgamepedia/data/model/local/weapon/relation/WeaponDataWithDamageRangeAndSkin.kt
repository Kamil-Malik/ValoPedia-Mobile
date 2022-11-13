package com.lelestacia.valorantgamepedia.data.model.local.weapon.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponStatistic

data class WeaponDataWithDamageRangeAndSkin(

    @Embedded val weapon: Weapon,

    @Relation(
        parentColumn = PARENT_KEY,
        entityColumn = ENTITY_KEY
    )
    val skin : List<WeaponSkin>,

    @Relation(
        parentColumn = PARENT_KEY,
        entityColumn = ENTITY_KEY
    )
    val weaponStat: WeaponStatistic?,

    @Relation(
        parentColumn = PARENT_KEY,
        entityColumn = ENTITY_KEY
    )
    val damageRange: List<DamageRange>
) {
    companion object {
        private const val PARENT_KEY = "uuid"
        private const val ENTITY_KEY = "weapon_uuid"
    }
}