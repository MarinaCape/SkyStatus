package com.skystatus.presentation.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.skystatus.databinding.BottomSheetDetailDayBinding
import com.skystatus.databinding.BottomSheetMenuBinding
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.presentation.core.BottomSheetDialog
import com.skystatus.presentation.utils.formatLocalDateTimeHours
import kotlin.math.roundToInt

class BottomSheetDetail(private val dailyForecast: DailyForecast) :
    BottomSheetDialog<BottomSheetDetailDayBinding>() {

    override fun viewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BottomSheetDetailDayBinding {
        return BottomSheetDetailDayBinding.inflate(inflater, container, false)
    }

    override fun initialize() {
        dialog?.setOnShowListener {
            val frameLayout = dialog?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            frameLayout?.let {
                BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
            }
            with(binding) {
                windText.text = "${dailyForecast.wind.speed.value} ${dailyForecast.wind.speed.unit}"
                snowText.text = "${dailyForecast.snowProbability}%"
                stormText.text = "${dailyForecast.thunderstormProbability}%"
                sunsetText.text = dailyForecast.sun.set.formatLocalDateTimeHours()

                windGutText.text = "${dailyForecast.windGut.value} ${dailyForecast.windGut.unit}"
                cloudText.text = "${dailyForecast.cloudCover}%"
                humedityText.text = "${dailyForecast.hoursOfRain}h"
                sunriseText.text = dailyForecast.sun.rise.formatLocalDateTimeHours()

                realTemperatureMinText.text = "${dailyForecast.realFeelTemperature.minimum.value.roundToInt()}º"
                realTemperatureMaxText.text = "${dailyForecast.realFeelTemperature.maximum.value.roundToInt()}º"
                probabilityRainText.text = "${dailyForecast.precipitationProbability}%"
                rainText.text = "${dailyForecast.rain.value} ${dailyForecast.rain.unit}"
            }
        }
    }
}