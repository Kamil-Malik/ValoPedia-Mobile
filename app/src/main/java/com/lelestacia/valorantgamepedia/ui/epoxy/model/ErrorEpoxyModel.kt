package com.lelestacia.valorantgamepedia.ui.epoxy.model

import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.databinding.ScreenErrorBinding
import com.lelestacia.valorantgamepedia.ui.epoxy.ViewBindingKotlinModel
import com.lelestacia.valorantgamepedia.utility.EpoxyError

data class ErrorEpoxyModel(private val error: EpoxyError) : ViewBindingKotlinModel<ScreenErrorBinding>(R.layout.screen_error) {

    override fun ScreenErrorBinding.bind() {
        tvTitle.text = error.title
        tvMessage.text = error.description
    }
}
