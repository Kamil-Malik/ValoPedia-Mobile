package com.lelestacia.valorantgamepedia.data.model.local.weapon_data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponStatistic
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.relation.WeaponDataWithDamageRangeAndSkin

@Dao
interface WeaponDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeaponSkin(weaponSkin: WeaponSkin)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamageRange(damageRange: DamageRange)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeaponStat(weaponStatistic: WeaponStatistic)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeaponData(weapon: Weapon)

    @Query("SELECT * FROM weapon_table ORDER BY name")
    suspend fun getListOfWeaponData() : List<Weapon>

    @Transaction
    @Query("SELECT * FROM weapon_table WHERE uuid = :uuid")
    suspend fun getSpecificWeaponDetail(uuid: String): WeaponDataWithDamageRangeAndSkin
}