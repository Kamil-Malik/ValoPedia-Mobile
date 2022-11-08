package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkWeaponData(
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
    val networkWeaponStat: NetworkWeaponStat?
): Parcelable