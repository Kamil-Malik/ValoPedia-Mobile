package com.lelestacia.valorantgamepedia.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.lelestacia.valorantgamepedia.databinding.FragmentHomeBinding
import com.lelestacia.valorantgamepedia.ui.epoxy.controller.NewsController
import com.lelestacia.valorantgamepedia.ui.viewmodel.HomeViewModel
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment(){

    private val viewModel by activityViewModels<HomeViewModel>()
    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = NewsController()
        val newsAdapter = controller.adapter
        binding.apply {
            rvNews.adapter = newsAdapter
            rvNews.layoutManager = LinearLayoutManager(context)
        }

        viewModel.getPagedNews().observe(viewLifecycleOwner) {
            when(it) {
                is FinalResponse.GenericException -> Unit
                is FinalResponse.HttpException -> Unit
                FinalResponse.IoException -> Unit
                FinalResponse.Loading -> Unit
                is FinalResponse.Success -> {
                    lifecycleScope.launchWhenCreated {
                        it.data.collectLatest { news ->
                            controller.submitData(news)
                        }
                    }
                }
            }
        }
    }
}