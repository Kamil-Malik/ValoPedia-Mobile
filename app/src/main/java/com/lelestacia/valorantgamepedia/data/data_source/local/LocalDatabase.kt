package com.lelestacia.valorantgamepedia.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lelestacia.valorantgamepedia.data.model.local.agent.dao.AgentDao
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Ability
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.converter.StringConverter
import com.lelestacia.valorantgamepedia.data.model.local.maps.dao.MapDao
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map
import com.lelestacia.valorantgamepedia.data.model.local.news.dao.NewsDao
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.data.model.local.weapon.dao.WeaponDao
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.WeaponStatistic

@Database(
    entities = [Map::class, Agent::class,
        Ability::class, Weapon::class,
        WeaponStatistic::class, WeaponSkin::class,
        DamageRange::class, News::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [StringConverter::class])
abstract class LocalDatabase : RoomDatabase() {

    abstract fun mapDao(): MapDao

    abstract fun agentDao(): AgentDao

    abstract fun weaponDao(): WeaponDao

    abstract fun newsDao(): NewsDao
}