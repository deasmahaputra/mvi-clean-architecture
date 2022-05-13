package com.deas.mylibrary.domain.repository

import com.deas.core.base.DataState
import com.deas.mylibrary.common.api.HomeApiHelper
import com.deas.mylibrary.domain.model.Categories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiHelper: HomeApiHelper
) : CategoryRepository {

    override suspend fun getCategories(): Flow<DataState<Categories>>{
        return flow {
            try {
                val data = apiHelper.getCategories()
                emit(DataState.Success(data))
            }catch (e : Exception){

            }
        }
    }
}