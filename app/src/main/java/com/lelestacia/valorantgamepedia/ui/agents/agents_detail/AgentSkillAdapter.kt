package com.lelestacia.valorantgamepedia.ui.agents.agents_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAbility
import com.lelestacia.valorantgamepedia.databinding.ItemSkillActiveBinding
import com.lelestacia.valorantgamepedia.databinding.ItemSkillPassiveBinding
import com.lelestacia.valorantgamepedia.usecases.SkillType

class AgentSkillAdapter : ListAdapter<LocalAbility, ViewHolder>(DIFF_CALLBACK) {

    class ViewHolderActive(private val binding: ItemSkillActiveBinding) :
        ViewHolder(binding.root) {
        fun bind(item: LocalAbility) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .fitCenter()
                    .into(ivAgentSkillIcon)

                tvAgentSkillTitle.text = item.displayName
                tvAgentSkillSlot.text = SkillType().get(item.slot)
                tvAgentSkillDescription.text = item.description
            }
        }
    }

    class ViewHolderPassive(private val binding: ItemSkillPassiveBinding) :
        ViewHolder(binding.root) {

        fun bind(item: LocalAbility) {
            binding.apply {
                tvAgentSkillTitle.text = item.displayName
                tvAgentSkillDescription.text = item.description
                tvAgentSkillSlot.text = SkillType().get(item.slot)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val skillType = SkillType().get(getItem(position).slot)
        return if (skillType == "Passive Skill") {
            PASSIVE_SKILL
        } else {
            ACTIVE_SKILL
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == PASSIVE_SKILL) {
            val binding = ItemSkillPassiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderPassive(binding)
        } else {
            val binding =
                ItemSkillActiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderActive(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.itemViewType == PASSIVE_SKILL) {
            (holder as ViewHolderPassive).bind(getItem(position))
        } else {
            (holder as ViewHolderActive).bind(getItem(position))
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalAbility>() {
            override fun areItemsTheSame(oldItem: LocalAbility, newItem: LocalAbility): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: LocalAbility, newItem: LocalAbility): Boolean {
                return oldItem.slot == newItem.slot
            }
        }
        private const val ACTIVE_SKILL = 1
        private const val PASSIVE_SKILL = 2
    }
}