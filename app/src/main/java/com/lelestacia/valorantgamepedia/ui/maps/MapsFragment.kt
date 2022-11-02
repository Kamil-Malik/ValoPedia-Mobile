package com.lelestacia.valorantgamepedia.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lelestacia.valorantgamepedia.databinding.FragmentMapsBinding
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapsFragment : Fragment() {

    private val viewModel by viewModels<MapsViewModel>()
    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        val adapter = MapsAdapter()
        binding.apply {
            rvMaps.adapter = adapter
            rvMaps.layoutManager = LinearLayoutManager(context)
        }

        viewModel.getMaps().observe(viewLifecycleOwner) {
            when (it) {
                is FinalResponse.GenericException -> Toast.makeText(
                    context,
                    "Error ${it.code} - ${it.cause}",
                    Toast.LENGTH_SHORT
                ).show()
                FinalResponse.Loading -> Unit
                FinalResponse.IoException -> Toast.makeText(
                    context,
                    "Silahkan cek koneksi",
                    Toast.LENGTH_SHORT
                ).show()
                is FinalResponse.Success -> adapter.submitList(it.data)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}