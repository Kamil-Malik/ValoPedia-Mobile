package com.lelestacia.valorantgamepedia.data.model.remote.agent


import com.google.gson.annotations.SerializedName

data class NetworkAgent(

    @SerializedName("abilities")
    val abilities: List<NetworkAgentAbility>,

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
    val networkRole: NetworkRole?,

    @SerializedName("uuid")
    val uuid: String,

    @SerializedName("voiceLine")
    val voiceLine: VoiceLine
)