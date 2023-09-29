package com.skystatus.presentation.home.model

import com.skystatus.domain.model.DailyForecast
import com.skystatus.domain.model.HourlyForecast

data class ForecastUI(val hours: List<HourlyForecast>, val dailyForecast: List<DailyForecast>)