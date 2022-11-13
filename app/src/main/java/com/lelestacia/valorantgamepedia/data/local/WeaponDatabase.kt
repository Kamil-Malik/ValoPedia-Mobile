package com.lelestacia.valorantgamepedia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.dao.WeaponDao
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponStatistic

@Database(
    entities = [Weapon::class, WeaponStatistic::class, WeaponSkin::class, DamageRange::class],
    version = 1,
    exportSchema = false
)
abstract class WeaponDatabase : RoomDatabase() {

    abstract fun weaponDao(): WeaponDao
}