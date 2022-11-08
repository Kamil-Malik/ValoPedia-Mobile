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
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponGridBinding
import com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail.DetailWeaponActivity

class WeaponGridAdapter :
    ListAdapter<NetworkWeaponData, WeaponGridAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemWeaponGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkWeaponData) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.drawable.ic_broken_image)
                    .into(ivWeaponIcon)
                tvWeaponTitle.text = item.displayName

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
        val binding = ItemWeaponGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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