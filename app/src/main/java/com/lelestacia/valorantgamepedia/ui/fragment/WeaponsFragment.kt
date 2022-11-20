package com.lelestacia.valorantgamepedia.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.databinding.FragmentWeaponsBinding
import com.lelestacia.valorantgamepedia.ui.activity.DetailWeaponActivity
import com.lelestacia.valorantgamepedia.ui.adapter.WeaponListAdapter
import com.lelestacia.valorantgamepedia.ui.epoxy.controller.WeaponController
import com.lelestacia.valorantgamepedia.ui.viewmodel.WeaponsViewModel
import com.lelestacia.valorantgamepedia.utility.EpoxyError
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeaponsFragment : Fragment(R.layout.fragment_weapons) {

    private val viewModel by viewModels<WeaponsViewModel>()
    private val binding: FragmentWeaponsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weaponController = WeaponController {
            startActivity(Intent(context, DetailWeaponActivity::class.java).apply {
                putExtra(WeaponListAdapter.WEAPON_UUID, it)
            })
        }

        binding.apply {
            rvWeapon.adapter = weaponController.adapter
            rvWeapon.layoutManager = LinearLayoutManager(context)
            rvWeapon.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        viewModel.getWeapons().observe(viewLifecycleOwner) {
            when (it) {
                is FinalResponse.HttpException -> weaponController.error = EpoxyError(
                    title = getString(R.string.epoxy_server_error_title),
                    description = getString(R.string.epoxy_server_error)
                )
                FinalResponse.Loading -> weaponController.isLoading = true
                FinalResponse.IoException -> weaponController.error = EpoxyError(
                    title = getString(R.string.epoxy_no_internet_error_title),
                    description = getString(R.string.epoxy_no_internet_error)
                )
                is FinalResponse.Success -> weaponController.listOfWeapons =
                    it.data as ArrayList<Weapon>
                is FinalResponse.GenericException -> weaponController.error = EpoxyError(
                    title = "I don't know what the fuck is going on",
                    description = it.cause as String
                )
            }
        }
    }
}