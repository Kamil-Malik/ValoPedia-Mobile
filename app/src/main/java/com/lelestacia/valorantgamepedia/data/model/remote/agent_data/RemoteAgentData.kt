package com.lelestacia.valorantgamepedia.data.model.remote.agent_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteAgentData(
    @SerializedName("abilities")
    val abilities: List<RemoteAbility>,
    @SerializedName("bustPortrait")
    val bustPortrait: String?,
    @SerializedName("characterTags")
    val characterTags: List<String>?,
    @SerializedName("description")
    val description: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("fullPortrait")
    val fullPortrait: String?,
    @SerializedName("isPlayableCharacter")
    val isPlayableCharacter: Boolean,
    @SerializedName("killfeedPortrait")
    val killfeedPortrait: String,
    @SerializedName("role")
    val role: Role?,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("voiceLine")
    val voiceLine: VoiceLine
) : Parcelable