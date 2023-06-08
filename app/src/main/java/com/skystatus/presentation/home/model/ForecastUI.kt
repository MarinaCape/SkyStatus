package com.skystatus.presentation.home.model

import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast

data class ForecastUI(val hours: List<HourlyForecast>, val dailyForecast: List<DailyForecast>)