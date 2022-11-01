package com.lelestacia.valorantgamepedia.ui.weapons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.weapons_info.WeaponsData
import com.lelestacia.valorantgamepedia.databinding.WeaponsItemBinding

class WeaponsAdapter : ListAdapter<WeaponsData, WeaponsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: WeaponsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WeaponsData) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivWeaponIcon)

                tvWeaponTitle.text = item.displayName
                tvWeaponCategory.text = itemView.context.getString(
                    R.string.weapon_category,
                    item.shopData?.category ?: "",
                    item.shopData?.categoryText ?: ""
                )
                val cost: String = if (item.shopData?.cost == null || item.shopData.cost == 0) {
                    "Non Purchasable"
                } else {
                    item.shopData.cost.toString()
                }
                tvWeaponPrice.text = cost
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = WeaponsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WeaponsData>() {
            override fun areItemsTheSame(oldItem: WeaponsData, newItem: WeaponsData): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: WeaponsData, newItem: WeaponsData): Boolean {
                return oldItem == newItem
            }
        }
    }
}