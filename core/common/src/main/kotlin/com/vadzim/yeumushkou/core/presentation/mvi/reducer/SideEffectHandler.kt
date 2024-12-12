package com.vadzim.yeumushkou.core.presentation.mvi.reducer

import com.vadzim.yeumushkou.core.presentation.mvi.SideEffect
import com.vadzim.yeumushkou.core.presentation.mvi.UiCommand
import com.vadzim.yeumushkou.core.presentation.mvi.UiEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalCoroutinesApi::class)
abstract class SideEffectHandler<out Event : UiEvent, out Command : UiCommand, in Effect : SideEffect> {

    private val domainSideEffectFlow = MutableSharedFlow<Effect>()
    private val uiSideEffectFlow = MutableSharedFlow<Effect>()

    fun getEventSource(): Flow<Event> {
        return domainSideEffectFlow
            .flatMapMerge { sideEffect -> handleDomainSideEffect(sideEffect) }
            .flowOn(Dispatchers.Default)
    }

    fun getCommandSource(): Flow<Command> {
        return uiSideEffectFlow
            .map { sideEffect -> handleUiSideEffect(sideEffect) }
            .flowOn(Dispatchers.Default)
    }

    suspend fun onDomainSideEffect(sideEffect: Effect) {
        domainSideEffectFlow.emit(sideEffect)
    }

    suspend fun onUiSideEffect(sideEffect: Effect) {
        uiSideEffectFlow.emit(sideEffect)
    }

    protected abstract suspend fun handleDomainSideEffect(sideEffect: Effect): Flow<Event>

    protected abstract suspend fun handleUiSideEffect(sideEffect: Effect): Command

}