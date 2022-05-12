package com.deas.mylibrary.presentation.viewmodel

import com.deas.core.base.mvi.BaseViewModel
import com.deas.mylibrary.domain.model.Categories
import com.deas.mylibrary.domain.usecase.CategoryUseCase
import com.deas.mylibrary.presentation.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryUseCase : CategoryUseCase) : BaseViewModel<HomeContract.Intent, HomeContract.ScreenState>() {

    private val _categories = MutableStateFlow(Categories())

    override fun createInitialState(): HomeContract.ScreenState {
        return HomeContract.ScreenState.Idle
    }

    override fun handleIntent(intent: HomeContract.Intent) {
        when(intent){
            is HomeContract.Intent.GetCategories -> getCategories()
        }
    }

    private fun getCategories(){

    }

}