package com.deas.mylibrary.domain.repository

import com.deas.mylibrary.common.api.HomeApiHelper
import com.deas.mylibrary.domain.model.Categories
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiHelper: HomeApiHelper
) : CategoryRepository {
    override suspend fun getCategories(): Categories = apiHelper.getCategories()
}