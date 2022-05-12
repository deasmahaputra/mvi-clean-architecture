package com.deas.mylibrary.domain.repository

import com.deas.mylibrary.domain.model.Categories

interface ApiHelper {

    suspend fun getCategories() : Categories
}