package com.skystatus.presentation.core

interface Renderable<VS : ViewState> {
    fun render(viewState: VS)
}