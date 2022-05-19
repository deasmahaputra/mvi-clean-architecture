package com.deas.mylibrary.presentation.contract

import com.deas.core.base.mvi.UiEffect
import com.deas.core.base.mvi.UiIntent
import com.deas.core.base.mvi.UiState
import com.deas.mylibrary.domain.model.Categories
import kotlinx.coroutines.flow.MutableStateFlow

class HomeContract {

    sealed class Intent : UiIntent {
        object GetCategories : Intent()
    }

    sealed class ScreenState : UiState {
        object Idle : ScreenState()
        data class Categories(val categoryState : CategoryState) : ScreenState()
        data class SideEffect(val sideEffect : HomeContract.SideEffect) : ScreenState()
    }

    sealed class CategoryState {
        object Idle : CategoryState()
        object Loading : CategoryState()
        data class Success(val categories : MutableStateFlow<Categories>) : CategoryState()
    }

    sealed class SideEffect : UiEffect {
        data class ShowError(val message : String) : SideEffect()
    }
}


