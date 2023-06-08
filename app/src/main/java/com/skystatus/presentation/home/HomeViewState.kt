package com.skystatus.presentation.home

import com.skystatus.presentation.core.ViewState
import com.skystatus.presentation.home.model.ForecastUI

sealed class HomeViewState : ViewState {
    class InitializeView(val forecast: ForecastUI) : HomeViewState()
    class Error(val message: String): HomeViewState()
}
