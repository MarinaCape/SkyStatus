package com.skystatus.presentation.home

import com.skystatus.presentation.core.ViewState

sealed class HomeViewState : ViewState {
    object InitializeView : HomeViewState()
}
