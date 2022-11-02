package com.lelestacia.valorantgamepedia.ui.maps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.MapsData
import com.lelestacia.valorantgamepedia.databinding.ItemMapBinding

class MapsAdapter : ListAdapter<MapsData, MapsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemMapBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MapsData) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.listViewIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivMapListIcon)

                tvMapName.text = item.displayName
                tvMapCoordinate.text = item.coordinates
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MapsData>() {
            override fun areItemsTheSame(oldItem: MapsData, newItem: MapsData): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: MapsData, newItem: MapsData): Boolean {
                return oldItem == newItem
            }
        }
    }
}