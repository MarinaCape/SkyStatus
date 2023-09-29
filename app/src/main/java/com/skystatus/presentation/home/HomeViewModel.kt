package com.skystatus.presentation.home

import com.skystatus.data.service.SettingsService
import com.skystatus.domain.core.fold
import com.skystatus.domain.model.City
import com.skystatus.domain.use_case.forecast.Get12HoursForecastUseCase
import com.skystatus.domain.use_case.forecast.Get5DaysForecastUseCase
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
        is HomeEvent.InitializeView -> initializeConfig(event.city)
        is HomeEvent.FavouriteLocation -> setFavouriteLocation(event.key)
    }

    private fun initializeConfig(city: City?) {
        launch {
            val idCity = if (city?.key?.isNotEmpty() != false)
                city?.key?.toInt() ?:307145
            else settingsService.cityFavourite?.toInt()

            if(idCity?.toString() == settingsService.cityFavourite){
                updateViewState(HomeViewState.ChangeFavIcon(true))
            }

            updateViewState(HomeViewState.Loading(true))
            get12HoursForecastUseCase(idCity?:307145).fold(
                onSuccess = { hours ->
                    get5DaysForecastUseCase(idCity?:307145).fold(
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

    private fun setFavouriteLocation(key: String?) {
        settingsService.cityFavourite = key?: "307145"
        updateViewState(HomeViewState.ChangeFavIcon(true))
    }
}