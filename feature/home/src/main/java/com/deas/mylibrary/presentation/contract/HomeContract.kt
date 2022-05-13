package com.deas.mylibrary.presentation.contract

import com.deas.core.base.mvi.UiIntent
import com.deas.core.base.mvi.UiState
import com.deas.data.model.Categories
import kotlinx.coroutines.flow.MutableStateFlow

class HomeContract {

    sealed class Intent : UiIntent {
        object GetCategories : Intent()
    }

    sealed class ScreenState : UiState {
        object Idle : ScreenState()
        object Loading : ScreenState()
        data class Categories(val categoryState : CategoryState) : ScreenState()
        data class SideEffect(val sideEffect : HomeContract.SideEffect) : ScreenState()
    }

    sealed class CategoryState {
        object Loading : CategoryState()
        data class Success(val categories : MutableStateFlow<Categories>) : CategoryState()
        data class Error(val errorMessage : String?) : CategoryState()
        data class Empty(val emptyMessage : String?) : CategoryState()
    }

    sealed class SideEffect {
        data class ShowError(val message : String) : SideEffect()
    }
}