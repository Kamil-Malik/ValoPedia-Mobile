package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.ShopData
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeapon

class MapWeapon {

    fun fromNetwork(networkWeapon: NetworkWeapon): Weapon {
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