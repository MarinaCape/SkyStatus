package com.skystatus.presentation.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skystatus.domain.core.main
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<VS : ViewState, I : Event> : ViewModel() {

    private val mutableViewState = MutableSharedFlow<VS?>()

    val viewState: SharedFlow<VS?>
        get() = mutableViewState

    protected fun updateViewState(viewState: VS) {
        viewModelScope.launch(main) {
            mutableViewState.emit(viewState)
        }
    }

    abstract fun onEvent(event: I)

    fun launch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(main) {
            block()
        }
    }
}