package com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.WeaponsData
import com.lelestacia.valorantgamepedia.databinding.ActivityDetailWeaponBinding
import com.lelestacia.valorantgamepedia.usecases.WeaponDamageRange
import com.lelestacia.valorantgamepedia.usecases.WeaponStat

class DetailWeaponActivity : AppCompatActivity() {

    private var _binding: ActivityDetailWeaponBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailWeaponBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val weaponStatAdapter = WeaponStatAdapter()
        val weaponDamageAdapter = WeaponDamageAdapter()
        val weaponSkinAdapter = WeaponSkinAdapter()

        val weapon = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("WEAPON", WeaponsData::class.java) as WeaponsData
        } else {
            intent.getParcelableExtra<WeaponsData>("WEAPON") as WeaponsData
        }

        binding.apply {

            if (weapon.weaponStats != null) {
                rvWeaponStat.setHasFixedSize(true)
                rvWeaponStat.adapter = weaponStatAdapter
                rvWeaponStat.layoutManager = object : GridLayoutManager(
                    this@DetailWeaponActivity,
                    2,
                    RecyclerView.VERTICAL,
                    false
                ) {

                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }

                rvWeaponDamageRange.setHasFixedSize(true)
                rvWeaponDamageRange.adapter = weaponDamageAdapter
                rvWeaponDamageRange.layoutManager = object : GridLayoutManager(
                    this@DetailWeaponActivity,
                    weapon.weaponStats.damageRanges.size + 1,
                    RecyclerView.VERTICAL,
                    false
                ) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
            } else {
                tvWeaponHeaderStat.visibility = View.GONE
                rvWeaponStat.visibility = View.GONE
                rvWeaponDamageRange.visibility = View.GONE
            }


            rvWeaponSkin.setHasFixedSize(true)
            rvWeaponSkin.adapter = weaponSkinAdapter
            rvWeaponSkin.layoutManager =
                GridLayoutManager(this@DetailWeaponActivity, 3, RecyclerView.HORIZONTAL, false)

            Glide.with(this@DetailWeaponActivity)
                .load(weapon.displayIcon)
                .placeholder(R.drawable.ic_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_broken_image)
                .into(ivWeaponIcon)
            tvWeaponTitle.text = weapon.displayName


            val fixedList = weapon.skins
                .filter { it.displayIcon != null }
                .filter {
                    it.displayName != getString(
                        R.string.standard_weapon_skin,
                        weapon.displayName
                    )
                }
                .filterNot { it.displayName.contains("Random") }
                .sortedBy { it.displayName }
            weaponSkinAdapter.submitList(fixedList)

            if (weapon.weaponStats != null) {
                weaponStatAdapter.submitList(WeaponStat().get(weapon.weaponStats))
                val weaponDamageRangeValue = WeaponDamageRange().get(weapon.weaponStats.damageRanges)
                weaponDamageAdapter.submitList(weaponDamageRangeValue)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}