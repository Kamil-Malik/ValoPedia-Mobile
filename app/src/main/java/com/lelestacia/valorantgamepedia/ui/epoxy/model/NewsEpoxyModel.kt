package com.lelestacia.valorantgamepedia.ui.epoxy.model

import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.databinding.ItemRowNewsBinding
import com.lelestacia.valorantgamepedia.ui.epoxy.ViewBindingKotlinModel
import com.lelestacia.valorantgamepedia.utility.DateFormatter
import java.util.*

data class NewsEpoxyModel(val news: News) :
    ViewBindingKotlinModel<ItemRowNewsBinding>(R.layout.item_row_news) {
    override fun ItemRowNewsBinding.bind() {
        tvTitle.text = news.title
        tvTimestamp.text = DateFormatter().format(news.date, TimeZone.getDefault().id)
        Glide.with(ivBanner)
            .load(news.bannerUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressNews.visibility = View.GONE
                    return false
                }

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
            .into(ivBanner)
    }
}
