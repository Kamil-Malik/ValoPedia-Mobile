package com.lelestacia.valorantgamepedia.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews
import com.lelestacia.valorantgamepedia.databinding.ItemRowNewsBinding
import com.lelestacia.valorantgamepedia.utility.DateFormatter
import java.util.*

class NewsAdapter : ListAdapter<NetworkNews, NewsAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(private val binding: ItemRowNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NetworkNews) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(item.bannerUrl)
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean = false

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressNews.visibility = View.GONE
                            return false
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .fitCenter()
                    .error(R.drawable.ic_broken_image)
                    .into(ivBanner)

                tvTitle.text = item.title
                tvTimestamp.text = DateFormatter().format(
                    item.date, TimeZone.getDefault().id
                )

//                btnShare.setOnClickListener {
//                    val intent = Intent(Intent.ACTION_SEND).apply {
//                        type = "text/plain"
//                        putExtra(Intent.EXTRA_SUBJECT, "Valopedia News")
//                        putExtra(Intent.EXTRA_TEXT, item.externalLink ?: item.url)
//                    }
//                    itemView.context.startActivity(intent)
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NetworkNews>() {
            override fun areItemsTheSame(oldItem: NetworkNews, newItem: NetworkNews): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: NetworkNews, newItem: NetworkNews): Boolean =
                oldItem == newItem
        }
    }
}