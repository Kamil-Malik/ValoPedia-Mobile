package com.lelestacia.valorantgamepedia.utility

class SkillType {

    fun get(skill: String): String {
        return when(skill) {
            SLOT_ONE -> "First Skill"
            SLOT_TWO -> "Second SKill"
            GRENADE -> "Grenade Skill"
            Ultimate -> "Ultimate Skill"
            else -> "Passive Skill"
        }
    }

    companion object {
        private const val SLOT_ONE = "Ability1"
        private const val SLOT_TWO = "Ability2"
        private const val GRENADE = "Grenade"
        private const val Ultimate = "Ultimate"
    }
}