package com.skystatus.presentation.main

import com.skystatus.data.service.SettingsService
import com.skystatus.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val settingsService: SettingsService,
) : BaseViewModel<MainViewState, MainEvent>() {

    override fun onEvent(event: MainEvent) = when (event) {
        is MainEvent.InitializeView -> fetchLocation()
    }

    private fun fetchLocation() {
        updateViewState(MainViewState.InitializeView)
    }
}