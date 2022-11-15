package com.lelestacia.valorantgamepedia.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lelestacia.valorantgamepedia.databinding.FragmentHomeBinding
import com.lelestacia.valorantgamepedia.ui.adapter.NewsAdapter
import com.lelestacia.valorantgamepedia.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<HomeViewModel>()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.isValidated.observe(viewLifecycleOwner) {
            when (it) {
                true -> Snackbar.make(
                    view, "News is updated", Snackbar.LENGTH_INDEFINITE
                ).show()
                false -> Snackbar.make(
                    view,
                    "News cannot be synchronized with server",
                    Snackbar.LENGTH_INDEFINITE
                ).show()
            }
        }

        val newsAdapter = NewsAdapter()
        binding.apply {
            rvNews.adapter = newsAdapter
            rvNews.layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launchWhenCreated {
            viewModel.getPagedNews().collect {
                newsAdapter.submitData(lifecycle, it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}