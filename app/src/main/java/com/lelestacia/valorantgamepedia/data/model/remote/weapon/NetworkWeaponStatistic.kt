package com.lelestacia.valorantgamepedia.data.model.remote.weapon


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkWeaponStatistic(
    @SerializedName("adsStats")
    val networkAdsStatistic: NetworkAdsStatistic?,
    @SerializedName("airBurstStats")
    val networkAirBurst: NetworkAirBurst?,
    @SerializedName("altFireType")
    val altFireType: String?,
    @SerializedName("altShotgunStats")
    val networkAltShotgunStatistic: NetworkAltShotgunStatistic?,
    @SerializedName("damageRanges")
    val networkDamageRanges: List<NetworkDamageRange>,
    @SerializedName("equipTimeSeconds")
    val equipTimeSeconds: Double,
    @SerializedName("feature")
    val feature: String?,
    @SerializedName("fireMode")
    val fireMode: String?,
    @SerializedName("fireRate")
    val fireRate: Double,
    @SerializedName("firstBulletAccuracy")
    val firstBulletAccuracy: Double,
    @SerializedName("magazineSize")
    val magazineSize: Int,
    @SerializedName("reloadTimeSeconds")
    val reloadTimeSeconds: Double,
    @SerializedName("runSpeedMultiplier")
    val runSpeedMultiplier: Double,
    @SerializedName("shotgunPelletCount")
    val shotgunPelletCount: Int,
    @SerializedName("wallPenetration")
    val wallPenetration: String
): Parcelable