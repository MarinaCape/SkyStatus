package com.skystatus.presentation.main

import com.skystatus.presentation.core.Event

sealed class MainEvent : Event {
    object InitializeView : MainEvent()
}
