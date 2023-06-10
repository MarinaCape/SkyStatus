package com.skystatus.presentation.sunset

import com.skystatus.domain.entity.City
import com.skystatus.presentation.core.ViewState
import com.skystatus.presentation.home.model.ForecastUI

sealed class SunsetViewState : ViewState {
    object InitializeView: SunsetViewState()
    class Error(val message: String): SunsetViewState()
    class Loading(val show: Boolean): SunsetViewState()
}
