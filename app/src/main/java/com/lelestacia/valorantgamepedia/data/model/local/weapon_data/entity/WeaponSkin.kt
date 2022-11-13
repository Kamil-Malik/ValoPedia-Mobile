package com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skin_table")
data class WeaponSkin(

    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "uuid") val uuid: String,

    @ColumnInfo(name = "name") val displayName: String,

    @ColumnInfo(name = "icon") val displayIcon: String,

    @ColumnInfo(name = "weapon_uuid") val weaponUUID: String
)
