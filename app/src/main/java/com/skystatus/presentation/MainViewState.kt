package com.skystatus.presentation

import com.skystatus.presentation.core.ViewState

sealed class MainViewState : ViewState {
    object InitializeView : MainViewState()
}
