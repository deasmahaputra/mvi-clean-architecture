package com.deas.mylibrary.domain.repository

import com.deas.core.base.DataState
import com.deas.data.model.Categories
import com.deas.data.repository.LocalDataSource
import com.deas.mylibrary.common.api.HomeApiHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiHelper: HomeApiHelper,
    private val localDataSource : LocalDataSource
) : CategoryRepository {

    override suspend fun getCategories(): Flow<DataState<Categories>>{
        return flow {
            try {
                val data = apiHelper.getCategories()
                val localData = localDataSource.getItems()
                if(localData.isEmpty()){
                    localDataSource.addItem(data)
                }
                emit(DataState.Success(data))
            }catch (e : Exception){

            }
        }
    }
}