package com.lelestacia.valorantgamepedia.data.model.local.weapon_data.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponStatistic

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