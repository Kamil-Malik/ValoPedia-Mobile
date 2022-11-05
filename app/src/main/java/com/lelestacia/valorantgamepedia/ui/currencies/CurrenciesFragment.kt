package com.lelestacia.valorantgamepedia.ui.currencies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.databinding.FragmentCurrenciesBinding
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment : Fragment() {

    private val viewModel by viewModels<CurrenciesViewModel>()
    private var _binding: FragmentCurrenciesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrenciesBinding.inflate(inflater, container, false)

        val adapter = CurrencyAdapter()
        binding.apply {
            rvCurrency.adapter = adapter
            rvCurrency.layoutManager = GridLayoutManager(context, 3)
        }
        viewModel.getCurrencies().observe(viewLifecycleOwner) {
            when (it) {
                is FinalResponse.GenericException -> Toast.makeText(
                    context,
                    getString(R.string.error_http, it.code, it.cause),
                    Toast.LENGTH_SHORT
                ).show()
                FinalResponse.IoException -> Toast.makeText(
                    context,
                    getString(R.string.error_connection),
                    Toast.LENGTH_SHORT
                ).show()
                FinalResponse.Loading -> Unit
                is FinalResponse.Success -> adapter.submitList(it.data)
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}