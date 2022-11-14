package com.lelestacia.valorantgamepedia.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponBinding
import com.lelestacia.valorantgamepedia.ui.activity.DetailWeaponActivity

class WeaponListAdapter : ListAdapter<Weapon, WeaponListAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemWeaponBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Weapon) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivWeaponIcon)

                tvWeaponTitle.text = item.displayName
                tvWeaponCategory.text = itemView.context.getString(
                    R.string.weapon_category,
                    item.shopData.category,
                    item.shopData.categoryText
                )

                tvWeaponPrice.text =
                    if (item.shopData.cost == 0)
                        "Non Purchasable"
                    else
                        item.shopData.cost.toString()

                root.setOnClickListener {
                    with(itemView.context) {
                        startActivity(Intent(this, DetailWeaponActivity::class.java).also {
                            it.putExtra(WEAPON_UUID, item.uuid)
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
        const val WEAPON_UUID = "WEAPON"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Weapon>() {
            override fun areItemsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Weapon, newItem: Weapon): Boolean {
                return oldItem == newItem
            }
        }
    }
}