package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeaponsData(
    @SerializedName("category")
    val category: String,
    @SerializedName("defaultSkinUuid")
    val defaultSkinUuid: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("killStreamIcon")
    val killStreamIcon: String,
    @SerializedName("shopData")
    val shopData: ShopData?,
    @SerializedName("skins")
    val skins: List<Skin>,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("weaponStats")
    val weaponStats: WeaponStats?
): Parcelable