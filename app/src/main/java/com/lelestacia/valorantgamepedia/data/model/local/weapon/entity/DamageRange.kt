package com.lelestacia.valorantgamepedia.data.model.local.weapon.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "damage_table", primaryKeys = ["weapon_uuid", "range"])
data class DamageRange(

    @ColumnInfo(name = "range") val range: String,

    @ColumnInfo(name = "head") val headDamage: String,

    @ColumnInfo(name = "body") val bodyDamage : String,

    @ColumnInfo(name = "leg") val legDamage: String,

    @ColumnInfo(name = "weapon_uuid") val weaponUUID: String
)
