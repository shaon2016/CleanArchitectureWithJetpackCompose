package com.shaon2016.core.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewSideEffect

abstract class BaseViewModel<EVENT : ViewEvent, EFFECT : ViewSideEffect, STATE : ViewState>() :
    ViewModel() {

    // Event
    private val _event = MutableSharedFlow<EVENT>()

    abstract fun handleEvents(event: EVENT)

    fun setEvent(event: EVENT) {
        viewModelScope.launch { _event.emit(event) }
    }

    private fun collectEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    init {
        collectEvents()
    }

    // Effect
    private val _effect = MutableSharedFlow<EFFECT>()
    val effect = _effect

    protected fun setEffect(effectHandler: () -> EFFECT) {
        val newEffect = effectHandler()
        viewModelScope.launch { _effect.emit(newEffect) }
    }

    // State
    abstract fun setInitialState(): STATE

    private val initialState by lazy { setInitialState() }

    private val _uiState = mutableStateOf(initialState)
    val state get() = _uiState

    protected fun setState(stateHandler: STATE.() -> STATE) {
        val uiState = state.value.stateHandler()
        _uiState.value = uiState
    }


}