package com.vadzim.yeumushkou.core.presentation.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadzim.yeumushkou.core.presentation.mvi.SideEffect
import com.vadzim.yeumushkou.core.presentation.mvi.UiCommand
import com.vadzim.yeumushkou.core.presentation.mvi.UiEvent
import com.vadzim.yeumushkou.core.presentation.mvi.reducer.Reducer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class MviViewModel<UiState : com.vadzim.yeumushkou.core.presentation.mvi.UiState, Event : UiEvent, in Effect : SideEffect, Command : UiCommand>(
    private val reducer: Reducer<UiState, Event, Effect, Command>,
) : ViewModel() {

    val stateFlow: StateFlow<UiState> get() = mutableStateFlow.asStateFlow()
    protected abstract val mutableStateFlow: MutableStateFlow<UiState>

    private val state: UiState get() = mutableStateFlow.value

    init {
        reducer.sideEffectHandler
            .getEventSource()
            .onEach(::interact)
            .launchIn(viewModelScope)

        reducer.sideEffectHandler
            .getCommandSource()
            .onEach(::handleUiCommand)
            .launchIn(viewModelScope)

        viewModelScope.launch {
            delay(200)
            reducer.initialEvents().forEach(::interact)
        }
    }

    fun interact(event: Event) {
        onHandleEvent(event)
    }

    abstract fun handleUiCommand(command: Command)

    private fun updateState(state: UiState) {
        mutableStateFlow.tryEmit(state)
    }

    private fun onHandleEvent(event: Event) {
        viewModelScope.launch {
            updateState(reducer.reduce(state, event))
        }
    }
}