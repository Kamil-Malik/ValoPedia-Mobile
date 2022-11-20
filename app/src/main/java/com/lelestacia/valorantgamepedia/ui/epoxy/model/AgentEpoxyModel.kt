package com.lelestacia.valorantgamepedia.ui.epoxy.model

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.databinding.ItemRowAgentBinding
import com.lelestacia.valorantgamepedia.ui.epoxy.ViewBindingKotlinModel

data class AgentEpoxyModel(val agent: Agent, val onItemClick : (String) -> Unit) :
    ViewBindingKotlinModel<ItemRowAgentBinding>(R.layout.item_row_agent) {

    override fun ItemRowAgentBinding.bind() {
        tvAgentName.text = agent.displayName
        tvAgentRoleTitle.text = agent.role.displayName

        Glide.with(ivAgentIcon)
            .load(agent.displayIcon)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgress.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    circularProgress.visibility = View.GONE
                    return false
                }
            })
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(ivAgentIcon)

        Glide.with(ivAgentRole)
            .load(agent.role.displayIcon)
            .placeholder(R.drawable.ic_placeholder)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(ivAgentRole)

        root.setOnClickListener {
            onItemClick(agent.uuid)
        }
    }
}