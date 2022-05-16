package com.deas.data.repository

import com.deas.data.model.CategoriesDto


interface LocalDataSource {

    suspend fun addItem(category : CategoriesDto)

    suspend fun getItems() : List<CategoriesDto>

    suspend fun updateItem(category: CategoriesDto)

    suspend fun clearCachedItems()
}