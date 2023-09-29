package com.skystatus.presentation.sunset

import com.skystatus.domain.model.Sunset
import com.skystatus.presentation.core.ViewState

sealed class SunsetViewState : ViewState {
    class InitializeView(val sunset: Sunset): SunsetViewState()
    class Error(val message: String): SunsetViewState()
    class Loading(val show: Boolean): SunsetViewState()
}
