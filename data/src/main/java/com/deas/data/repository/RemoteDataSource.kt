package com.deas.data.repository

import com.deas.data.model.CategoriesDto

interface RemoteDataSource {

    suspend fun getCategory(): CategoriesDto
}