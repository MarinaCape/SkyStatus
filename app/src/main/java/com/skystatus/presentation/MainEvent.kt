package com.skystatus.presentation

import com.skystatus.presentation.core.Event

sealed class MainEvent : Event {
    object InitializeView : MainEvent()
}
