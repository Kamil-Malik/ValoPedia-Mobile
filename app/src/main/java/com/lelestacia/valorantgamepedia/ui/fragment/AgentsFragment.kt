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
import com.lelestacia.valorantgamepedia.databinding.FragmentAgentsBinding
import com.lelestacia.valorantgamepedia.ui.adapter.AgentsAdapter
import com.lelestacia.valorantgamepedia.ui.viewmodel.AgentsViewModel
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsFragment : Fragment() {

    private val viewModel by viewModels<AgentsViewModel>()
    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)
        subscribe()
        return binding.root
    }

    private fun subscribe() {
        lifecycleScope.launchWhenCreated {
            val adapter = AgentsAdapter()
            binding.apply {
                rvAgents.setHasFixedSize(true)
                rvAgents.adapter = adapter
                rvAgents.layoutManager = LinearLayoutManager(context)
            }
            viewModel.getAgents().collect {
                when (it) {
                    is FinalResponse.HttpException -> Toast.makeText(
                        context,
                        getString(R.string.error_http, it.code, it.cause),
                        Toast.LENGTH_SHORT
                    ).show().also { dismissLoading() }
                    FinalResponse.Loading -> Unit
                    FinalResponse.IoException -> Toast.makeText(
                        context,
                        getString(R.string.error_connection),
                        Toast.LENGTH_SHORT
                    ).show().also { dismissLoading() }
                    is FinalResponse.Success -> adapter.submitList(it.data)
                        .also { dismissLoading() }
                    is FinalResponse.GenericException -> Toast.makeText(
                        context,
                        it.cause,
                        Toast.LENGTH_SHORT
                    ).show().also { dismissLoading() }
                }
            }
        }
    }

    private fun dismissLoading() {
        binding.screenLoading.root.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}