package com.lelestacia.valorantgamepedia.data.model.local.agent_data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalAbility(
    val abilityDisplayName: String,
    val abilityDisplayIcon: String,
    val abilityDescription: String,
    val slot: String
) : Parcelable