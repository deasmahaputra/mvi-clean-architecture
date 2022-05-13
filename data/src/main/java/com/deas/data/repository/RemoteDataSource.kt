package com.deas.data.repository

import com.deas.data.model.Categories

interface RemoteDataSource {

    suspend fun getCategory() : Categories
}