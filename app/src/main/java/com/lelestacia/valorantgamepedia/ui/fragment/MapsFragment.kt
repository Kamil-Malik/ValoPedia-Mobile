package com.lelestacia.valorantgamepedia.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.databinding.FragmentMapsBinding
import com.lelestacia.valorantgamepedia.ui.adapter.MapsAdapter
import com.lelestacia.valorantgamepedia.ui.viewmodel.MapsViewModel
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private val viewModel by viewModels<MapsViewModel>()
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        val adapter = MapsAdapter()
        binding.apply {
            rvMaps.setHasFixedSize(true)
            rvMaps.adapter = adapter
            rvMaps.layoutManager = LinearLayoutManager(context)
        }

        viewModel.getMaps().observe(viewLifecycleOwner) {
            when (it) {
                is FinalResponse.HttpException -> Toast.makeText(
                    context,
                    getString(R.string.error_http, it.code, it.cause),
                    Toast.LENGTH_SHORT
                ).show().also { dismissLoading() }
                FinalResponse.Loading -> Unit
                FinalResponse.IoException -> Toast.makeText(
                    context, getString(R.string.error_connection), Toast.LENGTH_SHORT
                ).show().also { dismissLoading() }
                is FinalResponse.Success -> adapter.submitList(it.data).also { dismissLoading() }
                is FinalResponse.GenericException -> Toast.makeText(
                    context, it.cause, Toast.LENGTH_SHORT
                ).show().also { dismissLoading() }
            }
        }
        return binding.root
    }

    private fun dismissLoading() {
        binding.screenLoading.root.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}