package com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lelestacia.valorantgamepedia.data.model.local.LocalWeaponStat
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponStatBinding


class WeaponStatAdapter :
    ListAdapter<LocalWeaponStat, WeaponStatAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemWeaponStatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocalWeaponStat) {
            binding.apply {
                tvWeaponStatTitle.text = item.title
                tvWeaponStatValue.text = item.value
                tvWeaponStatCalculation.text = item.calculation
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemWeaponStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalWeaponStat>() {
            override fun areItemsTheSame(
                oldItem: LocalWeaponStat,
                newItem: LocalWeaponStat
            ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(
                oldItem: LocalWeaponStat,
                newItem: LocalWeaponStat
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}