package com.deas.data.repository

import com.deas.core.base.DataState
import com.deas.data.model.CategoriesDto
import com.deas.data.repository.LocalDataSource
import com.deas.domain.entity.CategoryEntity
import com.deas.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource : LocalDataSource,
    private val mapper : Mapper<CategoriesDto, CategoryEntity>
) : Repository {
    override suspend fun getCategories(): Flow<DataState<CategoryEntity>>{
        return flow {
            try {
                val data = remoteDataSource.getCategory()
                localDataSource.clearCachedItems()
                localDataSource.addItem(data)

                emit(DataState.Success(mapper.from(data)))
            }catch (e : Exception){
                try {
                    val local = localDataSource.getItems()
                    emit(DataState.Success(mapper.from(local[0])))
                }catch (ex : Exception){
                    emit(DataState.Error(ex))
                }
            }
        }
    }
}