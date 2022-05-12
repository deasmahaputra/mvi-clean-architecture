package com.deas.mylibrary.domain.repository

import com.deas.mylibrary.domain.model.Categories

interface CategoryRepository {

    suspend fun getCategories() : Categories
}