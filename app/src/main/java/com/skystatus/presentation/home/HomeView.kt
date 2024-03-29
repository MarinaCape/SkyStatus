package com.skystatus.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.skystatus.R
import com.skystatus.databinding.HomeFragmentBinding
import com.skystatus.domain.model.City
import com.skystatus.presentation.core.BaseFragment
import com.skystatus.presentation.home.detail.BottomSheetDetail
import com.skystatus.presentation.home.menu.BottomSheetMenu
import com.skystatus.presentation.home.model.ForecastUI
import com.skystatus.presentation.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class HomeView : BaseFragment<HomeFragmentBinding, HomeViewModel, HomeViewState>() {

    override val viewModel: HomeViewModel by viewModels()
    private val args: HomeViewArgs by navArgs()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(HomeEvent.InitializeView(args.cityArg))
    }

    override fun render(viewState: HomeViewState) = when (viewState) {
        is HomeViewState.InitializeView -> initialConfiguration(viewState.forecast)
        is HomeViewState.Error -> toastInfo(viewState.message)
        is HomeViewState.Loading -> toggleProgress(viewState.show)
        is HomeViewState.ChangeFavIcon -> fillFabIcon(viewState.filled)
    }

    private fun initialConfiguration(forecast: ForecastUI) {
        binding.apply {
            if (args.cityArg != null) {
                locationText.text =
                    "${(args.cityArg as City).localizedName}, ${(args.cityArg as City).country.localizedName}"
            }
            if (forecast.hours.isNotEmpty()) {
                currentDegreeText.text = "${forecast.hours[0].temperature.value.roundToInt()}º"
                shortPhraseText.text = forecast.hours[0].iconPhrase
                carouselHours.adapter = HourlyAdapter(forecast.hours)
            }
            minDegreeText.text =
                "${getString(R.string.min)} ${forecast.dailyForecast[0].temperature.minimum.value.roundToInt()}º"
            maxDegreeText.text =
                "${getString(R.string.max)} ${forecast.dailyForecast[0].temperature.maximum.value.roundToInt()}º"

            recyclerDays.adapter = DailyAdapter(forecast.dailyForecast) {
                BottomSheetDetail(it).show(this@HomeView)
            }

            binding.menuIcon.setOnClickListener {
                BottomSheetMenu(cityOption = {
                    val action = HomeViewDirections.homeToLocation()
                    findNavController().navigate(action)
                }, sunsetOption = {
                    val action = HomeViewDirections.homeToSunset()
                    findNavController().navigate(action)
                }).show(this@HomeView)
            }

            binding.fabIcon.setOnClickListener {
                viewModel.onEvent(HomeEvent.FavouriteLocation(args.cityArg?.key))
            }
        }
    }

    private fun toggleProgress(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
        binding.scrollView.visibility = if (!show) View.VISIBLE else View.GONE
    }

    private fun toastInfo(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        toggleProgress(false)
    }

    private fun fillFabIcon(filled: Boolean) {
        if (filled) {
            binding.fabIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_filled
                )
            )
        } else {
            binding.fabIcon.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_start_empty
                )
            )
        }
    }
}