package com.skystatus.presentation.main

import com.skystatus.presentation.core.ViewState

sealed class MainViewState : ViewState {
    object InitializeView : MainViewState()
}
