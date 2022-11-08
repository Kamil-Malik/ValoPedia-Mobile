package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAbility
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalRole
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Ability
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Role

class MapAgent {

    fun getAbility(ability: Ability): LocalAbility {
        return LocalAbility(
            abilityDisplayIcon = ability.displayIcon ?: "",
            abilityDisplayName = ability.displayName,
            abilityDescription = ability.description,
            slot = ability.slot
        )
    }

    fun getRole(role: Role) : LocalRole {
        return LocalRole(
            roleUUID = role.uuid,
            roleDisplayName = role.displayName,
            roleDisplayIcon = role.displayIcon,
            roleDescription = role.description
        )
    }
}