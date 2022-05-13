package com.deas.mylibrary.domain.repository

import com.deas.core.base.DataState
import com.deas.data.model.Categories
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

    suspend fun getCategories() : Flow<DataState<Categories>>
}