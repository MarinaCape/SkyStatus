package com.skystatus.presentation.sunset

import com.skystatus.domain.core.fold
import com.skystatus.domain.interactor.forecast.GetSearchCitiesUseCase
import com.skystatus.domain.interactor.sunset.GetSunsetQualityUseCase
import com.skystatus.presentation.core.BaseViewModel
import com.skystatus.presentation.location.LocationViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SunsetViewModel @Inject constructor(
    private val getSunsetQualityUseCase: GetSunsetQualityUseCase,
) : BaseViewModel<SunsetViewState, SunsetEvent>() {

    override fun onEvent(event: SunsetEvent) = when (event) {
        is SunsetEvent.InitializeView -> initializeConfig()
    }

    private fun initializeConfig() {
        launch {
            updateViewState(SunsetViewState.Loading(true))
            getSunsetQualityUseCase("40.7933949,-77.8600012").fold(
                onSuccess = {
                    updateViewState(SunsetViewState.InitializeView)
                    updateViewState(SunsetViewState.Loading(false))
                },
                onFailure = { updateViewState(SunsetViewState.Error(it.message ?: "")) }
            )
        }
        updateViewState(SunsetViewState.InitializeView)
    }

}