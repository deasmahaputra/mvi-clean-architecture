package com.deas.remote.source

import com.deas.data.model.CategoriesDto
import com.deas.data.repository.Mapper
import com.deas.data.repository.RemoteDataSource
import com.deas.remote.api.ApiService
import com.deas.remote.model.CategoryRemote
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService : ApiService,
    private val mapper : Mapper<CategoryRemote, CategoriesDto>) : RemoteDataSource {

    override suspend fun getCategory(): CategoriesDto {
        val networkData = apiService.getCategories()
        return mapper.from(networkData)
    }

}