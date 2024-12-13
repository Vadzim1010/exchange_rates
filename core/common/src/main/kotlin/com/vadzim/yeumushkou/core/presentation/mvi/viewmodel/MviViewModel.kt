package com.vadzim.yeumushkou.core.presentation.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vadzim.yeumushkou.core.presentation.mvi.SideEffect
import com.vadzim.yeumushkou.core.presentation.mvi.UiCommand
import com.vadzim.yeumushkou.core.presentation.mvi.UiState
import com.vadzim.yeumushkou.core.presentation.mvi.reducer.Reducer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.vadzim.yeumushkou.core.presentation.mvi.Event as MviEvent

abstract class MviViewModel<State : UiState, Event : MviEvent, in Effect : SideEffect, Command : UiCommand>(
    private val reducer: Reducer<State, Event, Effect, Command>,
) : ViewModel() {

    val stateFlow: StateFlow<State> get() = mutableStateFlow.asStateFlow()
    private val mutableStateFlow: MutableStateFlow<State> = MutableStateFlow(reducer.initialState())

    private val state: State get() = mutableStateFlow.value

    init {
        reducer.sideEffectHandler
            .getEventSource()
            .onEach(::interact)
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)

        reducer.sideEffectHandler
            .getCommandSource()
            .onEach(::handleUiCommand)
            .flowOn(Dispatchers.Main.immediate)
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

    private fun updateState(state: State) {
        mutableStateFlow.tryEmit(state)
    }

    private fun onHandleEvent(event: Event) {
        viewModelScope.launch {
            updateState(reducer.reduce(state, event))
        }
    }

}