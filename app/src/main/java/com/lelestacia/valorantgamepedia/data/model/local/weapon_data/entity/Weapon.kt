package com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weapon_table")
data class Weapon(

    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "uuid") val uuid: String,

    @ColumnInfo(name = "name") val displayName: String,

    @ColumnInfo(name = "icon") val displayIcon: String,

    @Embedded val shopData: ShopData
)