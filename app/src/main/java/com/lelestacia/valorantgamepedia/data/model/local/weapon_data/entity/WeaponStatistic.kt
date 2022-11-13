package com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = "statistic_table",
    primaryKeys = ["weapon_uuid", "magazine_size", "penetration"]
)
data class WeaponStatistic(

    @ColumnInfo(name = "penetration") val wallPenetration: String,

    @ColumnInfo(name = "shotgun_pellet_count") val shotgunPelletCount: Int,

    @ColumnInfo(name = "run_speed_multiplier") val runSpeedMultiplier: Double,

    @ColumnInfo(name = "reload_time") val reloadTimeSeconds: Double,

    @ColumnInfo(name = "magazine_size") val magazineSize: Int,

    @ColumnInfo(name = "first_bullet_accuracy") val firstBulletAccuracy: Double,

    @ColumnInfo(name = "rate_of_fire") val fireRate: Double,

    @ColumnInfo(name = "fire_mode") val fireMode: String,

    @ColumnInfo(name = "weapon_feature") val feature: String,

    @ColumnInfo(name = "equip_time") val equipTimeSeconds: Double,

    @ColumnInfo(name = "weapon_uuid") val weaponUUID: String
)
