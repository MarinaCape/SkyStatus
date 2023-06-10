package com.skystatus.presentation.sunset

import com.skystatus.domain.core.fold
import com.skystatus.domain.interactor.GetSearchCitiesUseCase
import com.skystatus.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SunsetViewModel @Inject constructor(
    private val getSearchCitiesUseCase: GetSearchCitiesUseCase,
) : BaseViewModel<SunsetViewState, SunsetEvent>() {

    override fun onEvent(event: SunsetEvent) = when (event) {
        is SunsetEvent.InitializeView -> initializeConfig()
    }

    private fun initializeConfig() {
        updateViewState(SunsetViewState.InitializeView)
    }

}