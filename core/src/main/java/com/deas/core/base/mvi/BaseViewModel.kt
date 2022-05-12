package com.deas.core.base.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<Intent : UiIntent, State : UiState> : ViewModel() {

    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    val currentState: State
        get() = uiState.value

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _intent : MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    /**
     * Start listening to Event
     */
    private fun subscribeIntent() {
        viewModelScope.launch {
            intent.collect {
                handleIntent(it)
            }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleIntent(intent : Intent)


    /**
     * Set new Event
     */
    fun setIntent(intent : Intent) {
        val newIntent = intent
        viewModelScope.launch { _intent.emit(newIntent) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    init {
        subscribeIntent()
    }
}