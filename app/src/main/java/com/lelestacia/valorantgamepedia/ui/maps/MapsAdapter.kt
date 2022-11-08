package com.lelestacia.valorantgamepedia.ui.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.databinding.ItemMapBinding

class MapsAdapter : ListAdapter<LocalMapData, MapsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemMapBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocalMapData) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.localMapListIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivMapListIcon)

                tvMapName.text = item.localMapDisplayName
                tvMapCoordinate.text = item.localMapCoordinate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMapBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LocalMapData>() {
            override fun areItemsTheSame(oldItem: LocalMapData, newItem: LocalMapData): Boolean {
                return oldItem.localMapUUID == newItem.localMapUUID
            }

            override fun areContentsTheSame(oldItem: LocalMapData, newItem: LocalMapData): Boolean {
                return oldItem == newItem
            }
        }
    }
}