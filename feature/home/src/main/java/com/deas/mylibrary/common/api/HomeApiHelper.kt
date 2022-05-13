package com.deas.mylibrary.common.api

import com.deas.data.model.Categories


interface HomeApiHelper {

    suspend fun getCategories() : Categories
}