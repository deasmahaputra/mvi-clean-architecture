package com.deas.mylibrary.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.deas.core.base.DataState
import com.deas.core.base.mvi.BaseViewModel
import com.deas.data.repository.Mapper
import com.deas.domain.entity.CategoryEntity
import com.deas.mylibrary.domain.model.Categories
import com.deas.domain.usecase.CategoryUseCase
import com.deas.mylibrary.presentation.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryUseCase : CategoryUseCase,
    private val mapper: Mapper<CategoryEntity, Categories>
    ) : BaseViewModel<HomeContract.Intent, HomeContract.ScreenState, HomeContract.SideEffect>() {

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
        viewModelScope.launch {
            categoryUseCase.execute("")
                .onStart {
                    emit(DataState.Loading)
                }
                .collect{ stateCategory ->
                    when(stateCategory) {
                        is DataState.Loading -> {
                            setState {
                                HomeContract.ScreenState.Categories(
                                    HomeContract.CategoryState.Loading
                                )
                            }
                        }
                        is DataState.Success -> {
                            _categories.emit(mapper.from(stateCategory.data))
                            setState {
                                stateCategory.data
                                HomeContract.ScreenState.Categories(
                                    HomeContract.CategoryState.Success(_categories)
                                )
                            }
                        }
                        is DataState.Error -> {
                            setState {
                                HomeContract.ScreenState.SideEffect(
                                    HomeContract.SideEffect.ShowError("")
                                )
                            }
                            setEffect { HomeContract.SideEffect.ShowError(message = stateCategory.exception.message?:"") }
                        }
                    }
                }
        }
    }

}