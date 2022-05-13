package com.deas.mylibrary.domain.usecase

import com.deas.core.base.DataState
import com.deas.core.base.mvi.BaseUseCase
import com.deas.core.base.mvi.IoDispatcher
import com.deas.data.model.Categories
import com.deas.mylibrary.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val categoryRepository : CategoryRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<Categories, String>(){

    override suspend fun buildRequest(params: String?): Flow<DataState<Categories>> {
        if(params == null){
            return flow {
                emit(DataState.Error(Exception("params can't be null")))
            }.flowOn(dispatcher)
        }
        return categoryRepository.getCategories().flowOn(dispatcher)
    }


}