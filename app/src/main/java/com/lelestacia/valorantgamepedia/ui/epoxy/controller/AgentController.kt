package com.lelestacia.valorantgamepedia.ui.epoxy.controller

import com.airbnb.epoxy.EpoxyController
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.ui.epoxy.model.AgentEpoxyModel
import com.lelestacia.valorantgamepedia.ui.epoxy.model.ErrorEpoxyModel
import com.lelestacia.valorantgamepedia.ui.epoxy.model.LoadingEpoxyModel
import com.lelestacia.valorantgamepedia.utility.EpoxyError

class AgentController(
    private val onClicked: (String) -> Unit
) : EpoxyController() {

    var listOfAgent = arrayListOf<Agent>()
        set(value) {
            field = value
            isLoading = false
            requestModelBuild()
        }

    var error : EpoxyError? = null
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

        if(error != null) {
            ErrorEpoxyModel(error as EpoxyError).id("error").addTo(this)
            return
        }

        listOfAgent.forEach { agent ->
            AgentEpoxyModel(agent, onClicked).id(agent.uuid).addTo(this)
        }
    }
}