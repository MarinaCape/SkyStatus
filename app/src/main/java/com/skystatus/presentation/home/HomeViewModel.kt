package com.skystatus.presentation.home

import com.skystatus.data.service.SettingsService
import com.skystatus.domain.core.fold
import com.skystatus.domain.interactor.Get12HoursForecastUseCase
import com.skystatus.domain.interactor.Get5DaysForecastUseCase
import com.skystatus.presentation.MainEvent
import com.skystatus.presentation.MainViewState
import com.skystatus.presentation.core.BaseViewModel
import com.skystatus.presentation.home.model.ForecastUI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val settingsService: SettingsService,
    private val get12HoursForecastUseCase: Get12HoursForecastUseCase,
    private val get5DaysForecastUseCase: Get5DaysForecastUseCase,
) : BaseViewModel<HomeViewState, HomeEvent>() {

    override fun onEvent(event: HomeEvent) = when (event) {
        is HomeEvent.InitializeView -> initializeConfig()
    }

    private fun initializeConfig() {
        launch {
            updateViewState(HomeViewState.Loading(true))
            get12HoursForecastUseCase(307145).fold(
                onSuccess = { hours ->
                    get5DaysForecastUseCase(307145).fold(
                        onSuccess = {
                            updateViewState(HomeViewState.InitializeView(ForecastUI(hours, it)))
                            updateViewState(HomeViewState.Loading(false))
                        },
                        onFailure = { updateViewState(HomeViewState.Error(it.message ?: "")) }
                    )
                },
                onFailure = { updateViewState(HomeViewState.Error(it.message ?: "")) }
            )
        }
    }
}