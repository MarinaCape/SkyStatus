package com.skystatus.presentation.home

import com.skystatus.domain.entity.City
import com.skystatus.presentation.core.Event

sealed class HomeEvent : Event {
    class InitializeView(val city: City?) : HomeEvent()
    class FavouriteLocation(val key: String?) : HomeEvent()
}
