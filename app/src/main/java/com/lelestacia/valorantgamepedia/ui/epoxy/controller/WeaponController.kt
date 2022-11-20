package com.lelestacia.valorantgamepedia.ui.epoxy.controller

import com.airbnb.epoxy.EpoxyController
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.ui.epoxy.model.ErrorEpoxyModel
import com.lelestacia.valorantgamepedia.ui.epoxy.model.LoadingEpoxyModel
import com.lelestacia.valorantgamepedia.ui.epoxy.model.WeaponEpoxyModel
import com.lelestacia.valorantgamepedia.utility.EpoxyError

class WeaponController(private val onClicked: (String) -> Unit) : EpoxyController() {

    var listOfWeapons = arrayListOf<Weapon>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    var error: EpoxyError? = null
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    var isLoading = false
        set(value) {
            field = value
            if (field) requestModelBuild()
        }
    
    override fun buildModels() {
        if (isLoading) {
            LoadingEpoxyModel().id("Loading").addTo(this)
            return
        }

        if (error != null) {
            ErrorEpoxyModel(error as EpoxyError).id("error").addTo(this)
            return
        }

        listOfWeapons.forEach {
            WeaponEpoxyModel(it, onClicked).id(it.uuid).addTo(this)
        }
    }
}