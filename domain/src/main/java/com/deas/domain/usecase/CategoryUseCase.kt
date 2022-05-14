package com.deas.domain.usecase

import com.deas.core.base.DataState
import com.deas.core.base.mvi.BaseUseCase
import com.deas.domain.entity.CategoryEntity
import com.deas.domain.qualifiers.IoDispatcher
import com.deas.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class CategoryUseCase @Inject constructor(
    private val repository : Repository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseUseCase<CategoryEntity, String>(){

    override suspend fun buildRequest(params: String?): Flow<DataState<CategoryEntity>> {
        if(params == null){
            return flow {
                emit(DataState.Error(Exception("params can't be null")))
            }.flowOn(dispatcher)
        }
        return repository.getCategories().flowOn(dispatcher)
    }


}