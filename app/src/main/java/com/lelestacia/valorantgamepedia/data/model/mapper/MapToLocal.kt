package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Ability
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Role
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.*
import com.lelestacia.valorantgamepedia.data.model.remote.agent.NetworkAgent
import com.lelestacia.valorantgamepedia.data.model.remote.maps.NetworkMap
import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews
import com.lelestacia.valorantgamepedia.data.model.remote.weapon.NetworkWeapon
import kotlinx.coroutines.*

class MapToLocal {

    suspend fun ability(
        networkAgent: NetworkAgent,
        coroutineDispatcher: CoroutineDispatcher
    ): List<Ability> {
        val arr = arrayListOf<Deferred<Ability>>()
        networkAgent.abilities.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    Ability(
                        displayName = it.displayName,
                        displayIcon = it.displayIcon ?: "",
                        description = it.description,
                        slot = it.slot,
                        agentUUID = networkAgent.uuid
                    )
                }
            )
        }
        return arr.awaitAll()
    }

    fun agent(networkAgent: NetworkAgent): Agent {
        return Agent(
            uuid = networkAgent.uuid,
            displayName = networkAgent.displayName,
            displayIcon = networkAgent.displayIcon,
            description = networkAgent.description,
            displayPortrait = networkAgent.fullPortrait ?: "",
            displayTag = networkAgent.characterTags ?: emptyList(),
            role = Role(
                roleID = networkAgent.networkRole?.uuid ?: "",
                displayName = networkAgent.networkRole?.displayName ?: "",
                displayIcon = networkAgent.networkRole?.displayIcon ?: "",
                roleDescription = networkAgent.networkRole?.description ?: ""
            )
        )
    }

    suspend fun damageRange(
        networkWeapon: NetworkWeapon,
        coroutineDispatcher: CoroutineDispatcher
    ): List<DamageRange> {
        val arr = arrayListOf<Deferred<DamageRange>>()
        networkWeapon.networkWeaponStatistic?.networkDamageRanges?.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    DamageRange(
                        range = "${it.rangeStartMeters} - ${it.rangeEndMeters}m",
                        headDamage = it.headDamage.toInt().toString(),
                        bodyDamage = it.bodyDamage.toString(),
                        legDamage = it.legDamage.toInt().toString(),
                        weaponUUID = networkWeapon.uuid
                    )
                }
            )
        }
        return arr.awaitAll()
    }

    fun map(networkMap: NetworkMap): Map {
        return Map(
            uuid = networkMap.uuid,
            displayName = networkMap.displayName,
            listIcon = networkMap.listIcon,
            displayCoordinate = networkMap.coordinate,
            displayIcon = networkMap.displayIcon ?: "",
            splashIcon = networkMap.splash
        )
    }

    fun news(networkNews: NetworkNews): News {
        return News(
            bannerUrl = networkNews.bannerUrl,
            category = networkNews.category,
            date = networkNews.date,
            externalLink = networkNews.externalLink ?: "",
            title = networkNews.title,
            url = networkNews.url
        )
    }

    suspend fun skin(
        networkWeapon: NetworkWeapon,
        coroutineDispatcher: CoroutineDispatcher
    ): List<WeaponSkin> {
        val arr = arrayListOf<Deferred<WeaponSkin>>()
        networkWeapon.networkSkins.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    WeaponSkin(
                        uuid = it.uuid,
                        displayName = it.displayName,
                        displayIcon =
                        if (it.displayName.contains("Standard")) {
                            networkWeapon.displayIcon
                        } else {
                            it.displayIcon ?: ""
                        },
                        weaponUUID = networkWeapon.uuid
                    )
                }
            )
        }
        return arr.awaitAll()
    }

    fun statistic(networkWeapon: NetworkWeapon): WeaponStatistic? {
        if (networkWeapon.networkWeaponStatistic == null)
            return null
        return WeaponStatistic(
            wallPenetration = networkWeapon.networkWeaponStatistic.wallPenetration,
            shotgunPelletCount = networkWeapon.networkWeaponStatistic.shotgunPelletCount,
            runSpeedMultiplier = networkWeapon.networkWeaponStatistic.runSpeedMultiplier,
            reloadTimeSeconds = networkWeapon.networkWeaponStatistic.reloadTimeSeconds,
            magazineSize = networkWeapon.networkWeaponStatistic.magazineSize,
            firstBulletAccuracy = networkWeapon.networkWeaponStatistic.firstBulletAccuracy,
            fireRate = networkWeapon.networkWeaponStatistic.fireRate,
            fireMode = networkWeapon.networkWeaponStatistic.fireMode ?: "",
            feature = networkWeapon.networkWeaponStatistic.feature ?: "",
            equipTimeSeconds = networkWeapon.networkWeaponStatistic.equipTimeSeconds,
            weaponUUID = networkWeapon.uuid
        )
    }

    fun weapon(networkWeapon: NetworkWeapon): Weapon {
        return Weapon(
            uuid = networkWeapon.uuid,
            displayName = networkWeapon.displayName,
            displayIcon = networkWeapon.displayIcon,
            shopData = ShopData(
                category = networkWeapon.shopData?.category ?: "",
                categoryText = networkWeapon.shopData?.categoryText ?: "",
                cost = networkWeapon.shopData?.cost ?: 0
            )
        )
    }
}