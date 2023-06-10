package com.skystatus.presentation.sunset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skystatus.databinding.HomeFragmentBinding
import com.skystatus.databinding.LocationFragmentBinding
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.presentation.core.BaseFragment
import com.skystatus.presentation.home.HomeViewDirections
import com.skystatus.presentation.home.menu.BottomSheetMenu
import com.skystatus.presentation.home.model.ForecastUI
import com.skystatus.presentation.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class SunsetView : BaseFragment<LocationFragmentBinding, SunsetViewModel, SunsetViewState>() {

    override val viewModel: SunsetViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = LocationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(SunsetEvent.InitializeView)
    }

    override fun render(viewState: SunsetViewState) = when (viewState) {
        is SunsetViewState.InitializeView -> initialConfiguration()
        is SunsetViewState.Error -> toastInfo(viewState.message)
        is SunsetViewState.Loading -> toggleProgress(viewState.show)
    }

    private fun initialConfiguration() {
        binding.apply {

        }
    }

    private fun toggleProgress(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
        binding.recyclerLocation.visibility = if (!show) View.VISIBLE else View.GONE
    }

    private fun toastInfo(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        toggleProgress(false)
    }
}