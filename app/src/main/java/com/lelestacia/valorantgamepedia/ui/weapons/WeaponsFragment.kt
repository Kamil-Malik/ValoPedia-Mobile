package com.lelestacia.valorantgamepedia.ui.weapons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.databinding.FragmentWeaponsBinding
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

        viewModel.getWeapons().observe(viewLifecycleOwner) {
            when (it) {
                is FinalResponse.GenericException -> Toast.makeText(
                    context, getString(R.string.error_http, it.code, it.cause), Toast.LENGTH_SHORT
                ).show()
                FinalResponse.IoException -> Toast.makeText(
                    context, getString(R.string.error_connection), Toast.LENGTH_SHORT
                ).show()
                FinalResponse.Loading -> Unit
                is FinalResponse.Success -> {
                    val rvType = viewModel.getListType()
                    if (!rvType) setGridAdapter(it.data)
                    else setLIstAdapter(it.data)
                }
            }
        }

        return binding.root
    }

    private fun setLIstAdapter(item: List<NetworkWeaponData>) {
        val adapter = WeaponListAdapter()
        binding.apply {
            rvWeapon.adapter = adapter
            rvWeapon.layoutManager = LinearLayoutManager(context)
            rvWeapon.setHasFixedSize(true)
            adapter.submitList(item)
        }
    }

    private fun setGridAdapter(item: List<NetworkWeaponData>) {
        val adapter = WeaponGridAdapter()
        binding.apply {
            rvWeapon.adapter = adapter
            rvWeapon.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            rvWeapon.setHasFixedSize(true)
            adapter.submitList(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}