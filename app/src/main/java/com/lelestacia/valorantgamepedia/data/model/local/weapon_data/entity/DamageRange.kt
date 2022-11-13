package com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "damage_table", primaryKeys = ["weapon_uuid", "range"])
data class DamageRange(

    @ColumnInfo(name = "range") val range: String,

    @ColumnInfo(name = "head") val headDamage: Int,

    @ColumnInfo(name = "body") val bodyDamage : Int,

    @ColumnInfo(name = "leg") val legDamage: Int,

    @ColumnInfo(name = "weapon_uuid") val weaponUUID: String
)
