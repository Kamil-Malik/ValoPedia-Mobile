package com.lelestacia.valorantgamepedia.ui.weapons

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponBinding
import com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail.DetailWeaponActivity

class WeaponListAdapter : ListAdapter<NetworkWeaponData, WeaponListAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemWeaponBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkWeaponData) {
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

                root.setOnClickListener {
                    with(itemView.context) {
                        startActivity(Intent(this, DetailWeaponActivity::class.java).also {
                            it.putExtra("WEAPON", item)
                        })
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWeaponBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NetworkWeaponData>() {
            override fun areItemsTheSame(oldItem: NetworkWeaponData, newItem: NetworkWeaponData): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: NetworkWeaponData, newItem: NetworkWeaponData): Boolean {
                return oldItem == newItem
            }
        }
    }
}