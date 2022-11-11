package com.lelestacia.valorantgamepedia.ui.agents

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.databinding.ItemAgentBinding
import com.lelestacia.valorantgamepedia.ui.agents.agents_detail.AgentsDetailActivity

class AgentsAdapter : ListAdapter<LocalAgentData, AgentsAdapter.AgentsViewHolder>(DIFF_CALLBACK) {

    inner class AgentsViewHolder(private val binding: ItemAgentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocalAgentData) {
            binding.apply {
                Glide.with(itemView.context).load(item.displayIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.ic_broken_image).fitCenter().into(ivAgentIcon)

                Glide.with(itemView.context).load(item.role.displayIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.ic_broken_image).fitCenter().into(ivAgentRole)

                tvAgentName.text = item.displayName
                tvAgentRoleTitle.text = item.role.displayName

                binding.root.setOnClickListener {
                    with(itemView.context) {
                        startActivity(Intent(this, AgentsDetailActivity::class.java)
                            .also { it.putExtra(AGENT_UUID, item.uuid) })
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
        val binding = ItemAgentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalAgentData>() {
            override fun areItemsTheSame(
                oldItem: LocalAgentData, newItem: LocalAgentData
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: LocalAgentData, newItem: LocalAgentData
            ): Boolean {
                return oldItem.uuid == newItem.uuid
            }

        }
        const val AGENT_UUID = "agentUUID"
    }
}