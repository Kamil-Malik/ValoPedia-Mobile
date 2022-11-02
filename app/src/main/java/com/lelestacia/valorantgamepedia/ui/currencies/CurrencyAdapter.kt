package com.lelestacia.valorantgamepedia.ui.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.CurrenciesData
import com.lelestacia.valorantgamepedia.databinding.ItemCurrencyBinding

class CurrencyAdapter : ListAdapter<CurrenciesData, CurrencyAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CurrenciesData) {
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
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CurrenciesData>() {
            override fun areItemsTheSame(
                oldItem: CurrenciesData,
                newItem: CurrenciesData
            ): Boolean {
                return oldItem.uuid == newItem.uuid
            }

            override fun areContentsTheSame(
                oldItem: CurrenciesData,
                newItem: CurrenciesData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}