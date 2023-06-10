package com.skystatus.presentation.location

import com.skystatus.presentation.core.Event

sealed class LocationEvent : Event {
    object InitializeView : LocationEvent()
    class SearchLocation(val text: String) : LocationEvent()
}
