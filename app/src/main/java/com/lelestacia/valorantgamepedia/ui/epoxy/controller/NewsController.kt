package com.lelestacia.valorantgamepedia.ui.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.ui.epoxy.model.NewsEpoxyModel

class NewsController : PagingDataEpoxyController<News>() {

    override fun buildItemModel(currentPosition: Int, item: News?): EpoxyModel<*> {
        return NewsEpoxyModel(item as News).id(item.title)
    }
}