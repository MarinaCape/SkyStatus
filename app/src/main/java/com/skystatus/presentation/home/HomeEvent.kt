package com.skystatus.presentation.home

import com.skystatus.presentation.core.Event

sealed class HomeEvent : Event {
    object InitializeView : HomeEvent()
}
