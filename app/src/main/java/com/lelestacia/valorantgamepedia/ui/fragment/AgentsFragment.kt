package com.lelestacia.valorantgamepedia.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lelestacia.valorantgamepedia.R
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.databinding.FragmentAgentsBinding
import com.lelestacia.valorantgamepedia.ui.epoxy.controller.AgentController
import com.lelestacia.valorantgamepedia.ui.viewmodel.AgentsViewModel
import com.lelestacia.valorantgamepedia.utility.EpoxyError
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentsFragment : Fragment(R.layout.fragment_agents) {

    private val viewModel by viewModels<AgentsViewModel>()
    private val binding : FragmentAgentsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
    }

    private fun subscribe() {
        lifecycleScope.launchWhenCreated {
            val epoxyAgentController = AgentController {
                findNavController().navigate(AgentsFragmentDirections.listToDetail(it))
            }

            binding.apply {
                rvAgents.setHasFixedSize(true)
                rvAgents.adapter = epoxyAgentController.adapter
                rvAgents.layoutManager = LinearLayoutManager(context)
                rvAgents.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            }

            viewModel.getAgents().collect {
                when (it) {
                    is FinalResponse.HttpException -> epoxyAgentController.error = EpoxyError(
                        title = getString(R.string.epoxy_server_error_title),
                        description = getString(R.string.epoxy_server_error)
                    )
                    FinalResponse.Loading -> epoxyAgentController.isLoading = true
                    FinalResponse.IoException -> epoxyAgentController.error = EpoxyError(
                        title = getString(R.string.epoxy_no_internet_error_title),
                        description = getString(R.string.epoxy_no_internet_error)
                    )
                    is FinalResponse.Success -> epoxyAgentController.listOfAgent =
                        it.data as ArrayList<Agent>
                    is FinalResponse.GenericException -> Toast.makeText(
                        context,
                        it.cause,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}