package com.lelestacia.valorantgamepedia.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.databinding.FragmentWeaponsBinding
import com.lelestacia.valorantgamepedia.ui.adapter.WeaponListAdapter
import com.lelestacia.valorantgamepedia.ui.viewmodel.WeaponsViewModel
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeaponsFragment : Fragment() {

    private val viewModel by viewModels<WeaponsViewModel>()
    private var _binding: FragmentWeaponsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeaponsBinding.inflate(inflater, container, false)
        lifecycleScope.launchWhenCreated {
            viewModel.getWeapons().collect {
                when (it) {
                    is FinalResponse.HttpException -> Toast.makeText(
                        context,
                        getString(R.string.error_http, it.code, it.cause),
                        Toast.LENGTH_SHORT
                    ).show().also { dismissLoading() }
                    FinalResponse.IoException -> Toast.makeText(
                        context, getString(R.string.error_connection), Toast.LENGTH_SHORT
                    ).show().also { dismissLoading() }
                    FinalResponse.Loading -> Unit
                    is FinalResponse.Success -> setLIstAdapter(it.data).also {
                        dismissLoading()
                    }
                    is FinalResponse.GenericException ->
                        Toast.makeText(context, it.cause, Toast.LENGTH_SHORT).show()
                            .also { dismissLoading() }
                }
            }
        }
        return binding.root
    }

    private fun dismissLoading() {
        binding.screenLoading.root.visibility = View.GONE
    }

    private fun setLIstAdapter(item: List<Weapon>) {
        val adapter = WeaponListAdapter()
        binding.apply {
            rvWeapon.adapter = adapter
            rvWeapon.layoutManager = LinearLayoutManager(context)
            rvWeapon.setHasFixedSize(true)
            adapter.submitList(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}