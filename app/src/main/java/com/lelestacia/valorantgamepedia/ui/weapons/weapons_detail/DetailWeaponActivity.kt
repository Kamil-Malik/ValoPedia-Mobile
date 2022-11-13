package com.lelestacia.valorantgamepedia.ui.weapons.weapons_detail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.DamageRange
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponSkin
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.WeaponStatistic
import com.lelestacia.valorantgamepedia.databinding.ActivityDetailWeaponBinding
import com.lelestacia.valorantgamepedia.ui.weapons.WeaponListAdapter
import com.lelestacia.valorantgamepedia.usecases.DisplayDamageRange
import com.lelestacia.valorantgamepedia.usecases.DisplayStatistic
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailWeaponActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailWeaponViewModel>()
    private var _binding: ActivityDetailWeaponBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailWeaponBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val uuid = intent.getStringExtra(WeaponListAdapter.WEAPON_UUID) ?: ""
        window.statusBarColor = ContextCompat.getColor(this, android.R.color.black)

        lifecycleScope.launchWhenCreated {
            val weaponDetail = viewModel.getWeaponDetail(uuid)
            setDetail(weaponDetail.weapon)
            setRvStatistic(weaponDetail.weaponStat)
            setRvDamageRange(weaponDetail.damageRange)
            setRvSkin(weaponDetail.skin)
        }

        binding.apply {

            /*if (weapon.networkWeaponStatistic != null) {
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
                    weapon.networkWeaponStatistic.networkDamageRanges.size + 1,
                    RecyclerView.VERTICAL,
                    false
                ) {
                    override fun canScrollVertically(): Boolean {
                        return  false
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


            val fixedList = weapon.networkSkins
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

            if (weapon.networkWeaponStatistic != null) {
                weaponStatAdapter.submitList(WeaponStat().get(weapon.networkWeaponStatistic))
                val weaponDamageRangeValue =
                    WeaponDamageRange().get(weapon.networkWeaponStatistic.networkDamageRanges)
                weaponDamageAdapter.submitList(weaponDamageRangeValue)
            }*/
        }
    }

    private fun setRvSkin(skin: List<WeaponSkin>) {
        val weaponSkinAdapter = WeaponSkinAdapter()
        binding.apply {
            if (skin.isNotEmpty()) {
                rvWeaponSkin.setHasFixedSize(true)
                rvWeaponSkin.adapter = weaponSkinAdapter
                rvWeaponSkin.layoutManager =
                    GridLayoutManager(this@DetailWeaponActivity, 3, RecyclerView.HORIZONTAL, false)
                val displayList = skin
                    .filter { it.displayIcon.isNotEmpty() }
                    .filterNot { it.displayName.contains("Random") }
                    .sortedBy { it.displayName }
                    .toList()
                weaponSkinAdapter.submitList(displayList)
            } else {
                tvHeaderWeaponSkin.visibility = View.GONE
                rvWeaponSkin.visibility = View.GONE
            }
        }
    }

    private fun setRvDamageRange(damageRange: List<DamageRange>) {
        val weaponDamageAdapter = WeaponDamageAdapter()
        binding.apply {
            if (damageRange.isNotEmpty()) {
                rvWeaponDamageRange.setHasFixedSize(true)
                rvWeaponDamageRange.adapter = weaponDamageAdapter
                rvWeaponDamageRange.layoutManager = object : GridLayoutManager(
                    this@DetailWeaponActivity,
                    damageRange.size + 1,
                    RecyclerView.VERTICAL,
                    false
                ) {
                    override fun canScrollVertically(): Boolean {
                        return false
                    }
                }
                weaponDamageAdapter.submitList(DisplayDamageRange().get(damageRange))
            } else rvWeaponDamageRange.visibility = View.GONE
        }
    }

    private fun setRvStatistic(weaponStat: WeaponStatistic?) {
        val weaponStatAdapter = WeaponStatAdapter()
        binding.apply {
            if (weaponStat != null && weaponStat.magazineSize > 0) {
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
                weaponStatAdapter.submitList(DisplayStatistic().get(weaponStat))
            } else {
                tvWeaponHeaderStat.visibility = View.GONE
                rvWeaponStat.visibility = View.GONE
            }
        }
    }

    private fun setDetail(weapon: Weapon) {
        binding.apply {
            Glide.with(this@DetailWeaponActivity)
                .load(weapon.displayIcon)
                .placeholder(R.drawable.ic_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.drawable.ic_broken_image)
                .into(ivWeaponIcon)
            tvWeaponTitle.text = weapon.displayName
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}