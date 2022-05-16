package com.deas.domain.repository

import com.deas.core.base.DataState
import com.deas.domain.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getCategories() : Flow<DataState<CategoryEntity>>
}