package com.skystatus.presentation.sunset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skystatus.databinding.SunsetFragmentBinding
import com.skystatus.domain.model.Sunset
import com.skystatus.presentation.core.BaseFragment
import com.skystatus.presentation.utils.formatLocalDateTimeHours
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class SunsetView : BaseFragment<SunsetFragmentBinding, SunsetViewModel, SunsetViewState>() {

    override val viewModel: SunsetViewModel by viewModels()

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = SunsetFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onEvent(SunsetEvent.InitializeView)
    }

    override fun render(viewState: SunsetViewState) = when (viewState) {
        is SunsetViewState.InitializeView -> initialConfiguration(viewState.sunset)
        is SunsetViewState.Error -> toastInfo(viewState.message)
        is SunsetViewState.Loading -> toggleProgress(viewState.show)
    }

    private fun initialConfiguration(sunset: Sunset) {
        binding.apply {
            hourText.text = sunset.dawnTime.formatLocalDateTimeHours()
            percentageDawn.progressDrawable = ContextCompat.getDrawable(requireContext(), sunset.sunsetInfo.color)
            percentageDawn.progress = sunset.quality_percent.roundToInt()
            percentageText.text = "${sunset.quality_percent.roundToInt()}%"
            temperatureText.text = "${sunset.temperature.roundToInt()}º"
            qualityShort.text = getString(sunset.sunsetInfo.title)
            descriptionText.text = getString(sunset.sunsetInfo.description)
            dawnType.text = getString(sunset.dawnType.type)

            closeImg.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun toggleProgress(show: Boolean) {
        binding.progress.visibility = if (show) View.VISIBLE else View.GONE
        binding.infoLayout.visibility = if (show) View.GONE else View.VISIBLE
        binding.descriptionLayout.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun toastInfo(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        toggleProgress(false)
    }
}