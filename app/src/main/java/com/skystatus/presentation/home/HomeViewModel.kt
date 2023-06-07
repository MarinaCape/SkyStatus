package com.skystatus.presentation.home

import com.skystatus.data.service.SettingsService
import com.skystatus.presentation.MainEvent
import com.skystatus.presentation.MainViewState
import com.skystatus.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val settingsService: SettingsService,
) : BaseViewModel<HomeViewState, HomeEvent>() {

    override fun onEvent(event: HomeEvent) = when (event) {
        is HomeEvent.InitializeView -> fetchLocation()
    }

    private fun fetchLocation() {
        updateViewState(HomeViewState.InitializeView)
    }
}