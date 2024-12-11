package com.vadzim.yeumushkou.core.presentation.mvi.reducer

import com.vadzim.yeumushkou.core.presentation.mvi.SideEffect
import com.vadzim.yeumushkou.core.presentation.mvi.UiCommand
import com.vadzim.yeumushkou.core.presentation.mvi.UiEvent
import com.vadzim.yeumushkou.core.presentation.mvi.UiState

interface Reducer<State : UiState, Event : UiEvent, Effect : SideEffect, out Command : UiCommand> {

    val sideEffectHandler: SideEffectHandler<Event, Command, Effect>

    suspend fun reduce(state: State, event: Event): State

    fun initialEvents(): List<Event>

    fun initialState(): State
}