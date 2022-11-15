package com.lelestacia.valorantgamepedia.data.model.local.weapon.dao

import androidx.room.*
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponStatistic
import com.lelestacia.valorantgamepedia.data.model.local.weapon.relation.WeaponDataWithDamageRangeAndSkin

@Dao
interface WeaponDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfWeaponSkin(weaponSkin: List<WeaponSkin>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfDamageRange(damageRange: List<DamageRange>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfWeaponStatistic(weaponStatistic: List<WeaponStatistic>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOFWeaponData(weapon: List<Weapon>)

    @Query("SELECT * FROM weapon_table ORDER BY name")
    suspend fun getListOfWeaponData() : List<Weapon>

    @Transaction
    @Query("SELECT * FROM weapon_table WHERE uuid = :uuid")
    suspend fun getSpecificWeaponDetail(uuid: String): WeaponDataWithDamageRangeAndSkin
}