package com.skystatus.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.skystatus.databinding.HomeFragmentBinding
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.presentation.core.BaseFragment
import com.skystatus.presentation.home.model.ForecastUI
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
        is HomeViewState.InitializeView -> initialConfiguration(viewState.forecast)
        is HomeViewState.Error -> toastInfo(viewState.message)
    }

    private fun initialConfiguration(forecast: ForecastUI) {
        binding.apply {
            if(forecast.hours.isNotEmpty()){
                currentDegreeText.text = forecast.hours[0].temperature.value.toString()
                minDegreeText.text = forecast.hours[0].temperature.value.toString()

                carouselHours.adapter = HourlyAdapter(forecast.hours)
            }
            recyclerDays.adapter = DailyAdapter(forecast.dailyForecast)
        }
    }

    private fun toastInfo(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}