package com.deas.data.repository

import com.deas.data.model.Categories

interface LocalDataSource {

    suspend fun addItem(category : Categories)

    suspend fun getItems() : List<Categories>

    suspend fun updateItem(category: Categories)
}