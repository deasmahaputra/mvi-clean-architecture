package com.deas.mylibrary.domain.usecase

import com.deas.core.base.DataState
import com.deas.mylibrary.domain.model.Categories
import com.deas.mylibrary.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CategoryUseCase @Inject constructor(private val categoryRepository : CategoryRepository){

    operator fun invoke() : Flow<DataState<Categories>> = flow {
        emit(DataState.Loading)
        try {
            val response = categoryRepository.getCategories()
            emit(DataState.Success(response))
        }catch (e : Exception){
            emit(DataState.Error(e))
        }
    }


}