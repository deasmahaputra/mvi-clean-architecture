package com.deas.mylibrary.common.api

import com.deas.data.model.Categories
import javax.inject.Inject

class HomeApiHelperImpl @Inject constructor(private val apiService : HomeApiService) : HomeApiHelper {
    override suspend fun getCategories(): Categories = apiService.getCategories()

}