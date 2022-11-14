package com.lelestacia.valorantgamepedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.DamageRange
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponDamageBinding
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponDamageHeaderBinding

class DamageRangeAdapter : ListAdapter<DamageRange, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolderBody(private val binding: ItemWeaponDamageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DamageRange) {
            binding.apply {
                tvHeaderDamage.text = item.range
                tvHeadDamage.text = item.headDamage
                tvBodyDamage.text = item.bodyDamage
                tvLegDamage.text = item.legDamage
            }
        }
    }

    class ViewHolderHeader(private val binding: ItemWeaponDamageHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DamageRange) {
            binding.apply {
                tvHeaderDamage.text = item.range
                tvHeadDamage.text = item.headDamage
                tvBodyDamage.text = item.bodyDamage
                tvLegDamage.text = item.legDamage
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).range == "Damage") {
            HEADER
        } else {
            BODY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            val binding = ItemWeaponDamageHeaderBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ViewHolderHeader(binding)
        } else {
            val binding = ItemWeaponDamageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ViewHolderBody(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItem(position) != null) if (holder.itemViewType == HEADER) {
            (holder as ViewHolderHeader).bind(getItem(position))
        } else (holder as ViewHolderBody).bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DamageRange>() {
            override fun areItemsTheSame(
                oldItem: DamageRange, newItem: DamageRange
            ): Boolean {
                return newItem.range == oldItem.range
            }

            override fun areContentsTheSame(
                oldItem: DamageRange, newItem: DamageRange
            ): Boolean {
                return newItem == oldItem
            }

        }
        private const val HEADER = 1
        private const val BODY = 2
    }
}