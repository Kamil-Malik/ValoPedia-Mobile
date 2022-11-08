package com.lelestacia.valorantgamepedia.data.model.local.agent_data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocalRole(
    val roleUUID: String,
    val roleDisplayName: String,
    val roleDisplayIcon: String,
    val roleDescription: String
) : Parcelable
