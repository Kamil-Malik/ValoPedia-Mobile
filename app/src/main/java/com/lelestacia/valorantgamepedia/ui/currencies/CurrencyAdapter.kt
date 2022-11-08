package com.lelestacia.valorantgamepedia.ui.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.databinding.ItemCurrencyBinding

class CurrencyAdapter :
    ListAdapter<NetworkCurrencyData, CurrencyAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkCurrencyData) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.displayIcon)
                    .into(ivCurrencyIcon)

                tvCurrencyName.text = item.displayName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NetworkCurrencyData>() {
            override fun areItemsTheSame(
                oldItem: NetworkCurrencyData,
                newItem: NetworkCurrencyData
            ): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(
                oldItem: NetworkCurrencyData,
                newItem: NetworkCurrencyData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}