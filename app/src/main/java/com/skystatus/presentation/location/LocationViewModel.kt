package com.skystatus.presentation.location

import com.skystatus.domain.core.fold
import com.skystatus.domain.use_case.forecast.GetSearchCitiesUseCase
import com.skystatus.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getSearchCitiesUseCase: GetSearchCitiesUseCase,
) : BaseViewModel<LocationViewState, LocationEvent>() {

    override fun onEvent(event: LocationEvent) = when (event) {
        is LocationEvent.InitializeView -> initializeConfig()
        is LocationEvent.SearchLocation -> searchLocation(event.text)
    }

    private fun initializeConfig() {
        updateViewState(LocationViewState.InitializeView)
    }

    private fun searchLocation(text: String) {
        launch {
            updateViewState(LocationViewState.Loading(true))
            getSearchCitiesUseCase(text).fold(
                onSuccess = {
                    updateViewState(LocationViewState.ShowLocations(it))
                    updateViewState(LocationViewState.Loading(false))
                },
                onFailure = { updateViewState(LocationViewState.Error(it.message ?: "")) }
            )
        }
    }
}