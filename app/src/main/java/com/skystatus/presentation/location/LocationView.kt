package com.skystatus.presentation.location

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
class LocationView : BaseFragment<LocationFragmentBinding, LocationViewModel, LocationViewState>() {

    override val viewModel: LocationViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = LocationFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(LocationEvent.InitializeView)
    }

    override fun render(viewState: LocationViewState) = when (viewState) {
        is LocationViewState.InitializeView -> initialConfiguration()
        is LocationViewState.Error -> toastInfo(viewState.message)
        is LocationViewState.Loading -> toggleProgress(viewState.show)
        is LocationViewState.ShowLocations -> showLocations(viewState.cities)
    }

    private fun initialConfiguration() {
        binding.apply {
            searchText.addTextChangedListener {
                if(it.toString().length > 3) {
                    viewModel.onEvent(LocationEvent.SearchLocation(it.toString()))
                }
            }
        }
    }

    private fun showLocations(cities: List<City>) {
        binding.recyclerLocation.adapter = LocationAdapter(cities){
            val action = LocationViewDirections.locationToHome(it)
            findNavController().navigate(action)
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