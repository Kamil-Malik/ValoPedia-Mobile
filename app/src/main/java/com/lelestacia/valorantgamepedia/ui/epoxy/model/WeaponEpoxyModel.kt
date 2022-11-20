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
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.databinding.ItemWeaponBinding
import com.lelestacia.valorantgamepedia.ui.epoxy.ViewBindingKotlinModel

data class WeaponEpoxyModel(val weapon: Weapon, val onClicked: (String) -> Unit) :
    ViewBindingKotlinModel<ItemWeaponBinding>(R.layout.item_weapon) {

    override fun ItemWeaponBinding.bind() {
        Glide.with(ivWeaponIcon).load(weapon.displayIcon)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressLoading.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressLoading.visibility = View.GONE
                    return false
                }

            })
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(ivWeaponIcon)

        tvWeaponTitle.text = weapon.displayName
        tvWeaponCategory.text = root.context.getString(
            R.string.weapon_category, weapon.shopData.category, weapon.shopData.categoryText
        )
        tvWeaponPrice.text = if (weapon.shopData.cost == 0) "Non Purchasable"
        else weapon.shopData.cost.toString()
        root.setOnClickListener { onClicked(weapon.uuid) }
    }
}