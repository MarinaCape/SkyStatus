package com.skystatus.presentation.location

import com.skystatus.domain.entity.City
import com.skystatus.presentation.core.ViewState
import com.skystatus.presentation.home.model.ForecastUI

sealed class LocationViewState : ViewState {
    object InitializeView: LocationViewState()
    class Error(val message: String): LocationViewState()
    class Loading(val show: Boolean): LocationViewState()
    class ShowLocations(val cities: List<City>): LocationViewState()
}
