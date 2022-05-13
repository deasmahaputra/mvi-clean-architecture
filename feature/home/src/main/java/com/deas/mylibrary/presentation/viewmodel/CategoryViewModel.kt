package com.deas.mylibrary.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.deas.core.base.DataState
import com.deas.core.base.mvi.BaseViewModel
import com.deas.data.model.Categories
import com.deas.mylibrary.domain.usecase.CategoryUseCase
import com.deas.mylibrary.presentation.contract.HomeContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            categoryUseCase.execute("deas")
                .onStart {
                    emit(DataState.Loading)
                }
                .collect{ stateCategory ->
                    when(stateCategory) {
                        is DataState.Error -> {

                        }
                        is DataState.Loading -> {
                            setState {
                                HomeContract.ScreenState.Categories(
                                    HomeContract.CategoryState.Loading
                                )
                            }
                        }
                        is DataState.Success -> {
                            _categories.emit(stateCategory.data)
                            setState {
                                HomeContract.ScreenState.Categories(
                                    HomeContract.CategoryState.Success(_categories)
                                )
                            }

                        }
                    }
                }
        }
//        categoryUseCase().onEach { stateCategory ->
//            when(stateCategory) {
//                is DataState.Error -> {
//
//                }
//                is DataState.Loading -> {
//                    setState {
//                        HomeContract.ScreenState.Categories(
//                            HomeContract.CategoryState.Loading
//                        )
//                    }
//                }
//                is DataState.Success -> {
//                    _categories.emit(stateCategory.data)
//                    setState {
//                        HomeContract.ScreenState.Categories(
//                            HomeContract.CategoryState.Success(_categories)
//                        )
//                    }
//
//                }
//            }
//        }.launchIn(viewModelScope)
    }

}