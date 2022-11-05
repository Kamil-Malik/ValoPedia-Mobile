package com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.Skin
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponSkinBinding

class WeaponSkinAdapter : ListAdapter<Skin, WeaponSkinAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemWeaponSkinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Skin) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.ic_broken_image)
                    .into(ivWeaponSkinIcon)

                tvWeaponSkinTitle.text = item.displayName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemWeaponSkinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Skin>() {
            override fun areItemsTheSame(oldItem: Skin, newItem: Skin): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Skin, newItem: Skin): Boolean {
                return oldItem == newItem
            }
        }
    }
}