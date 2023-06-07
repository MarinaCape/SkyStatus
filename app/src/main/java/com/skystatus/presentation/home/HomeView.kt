package com.skystatus.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.skystatus.databinding.HomeFragmentBinding
import com.skystatus.presentation.core.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeView : BaseFragment<HomeFragmentBinding, HomeViewModel, HomeViewState>() {

    override val viewModel: HomeViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(HomeEvent.InitializeView)
    }

    override fun render(viewState: HomeViewState) = when (viewState) {
        is HomeViewState.InitializeView -> initialConfiguration()
    }

    private fun initialConfiguration() {
        binding.apply {

        }
    }
}