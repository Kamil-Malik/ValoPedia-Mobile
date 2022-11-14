package com.lelestacia.valorantgamepedia.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map
import com.lelestacia.valorantgamepedia.databinding.ItemMapBinding

class MapsAdapter : ListAdapter<Map, MapsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemMapBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Map) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.listIcon)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_broken_image)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivMapListIcon)

                tvMapName.text = item.displayName
                tvMapCoordinate.text = item.displayCoordinate
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Map>() {
            override fun areItemsTheSame(oldItem: Map, newItem: Map): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(oldItem: Map, newItem: Map): Boolean {
                return oldItem == newItem
            }
        }
    }
}