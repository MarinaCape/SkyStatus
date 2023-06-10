package com.skystatus.presentation.sunset

import com.skystatus.presentation.core.Event

sealed class SunsetEvent : Event {
    object InitializeView : SunsetEvent()
}
