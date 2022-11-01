package com.lelestacia.valorantgamepedia.ui.agents.agents_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.Ability
import com.lelestacia.valorantgamepedia.databinding.SkillItemBinding

class AgentSkillAdapter : ListAdapter<Ability, AgentSkillAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: SkillItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Ability) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_broken_image)
                    .fitCenter()
                    .into(ivAgentSkillIcon)

                tvAgentSkillTitle.text = item.displayName
                tvAgentSkillDescription.text = item.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SkillItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Ability>() {
            override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
                return oldItem.slot == newItem.slot
            }
        }
    }
}