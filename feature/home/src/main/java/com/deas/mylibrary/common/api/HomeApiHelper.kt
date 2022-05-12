package com.deas.mylibrary.common.api

import com.deas.mylibrary.domain.model.Categories

interface HomeApiHelper {

    suspend fun getCategories() : Categories
}