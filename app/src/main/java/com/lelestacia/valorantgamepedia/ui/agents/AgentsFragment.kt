package com.lelestacia.valorantgamepedia.ui.agents

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lelestacia.valorantgamepedia.databinding.FragmentAgentsBinding
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
        val adapter = AgentsAdapter()

        binding.rvAgents.adapter = adapter
        binding.rvAgents.layoutManager = LinearLayoutManager(context)
        viewModel.getAgents().observe(viewLifecycleOwner){
            when(it){
                is FinalResponse.GenericException -> Toast.makeText(
                    context,
                    "Error ${it.code} - ${it.cause}",
                    Toast.LENGTH_SHORT
                )
                    .show()
                FinalResponse.Loading -> Log.d(TAG, "onCreateView: Loading")
                FinalResponse.IoException -> Toast.makeText(context, "Silahkan cek koneksi", Toast.LENGTH_SHORT).show()
                is FinalResponse.Success -> {
                    val newData = it.data.filter { agent ->
                        agent.isPlayableCharacter
                    }
                    adapter.submitList(newData)
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}