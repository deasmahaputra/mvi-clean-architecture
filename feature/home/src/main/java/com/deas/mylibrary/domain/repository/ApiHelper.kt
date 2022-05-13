package com.deas.mylibrary.domain.repository

import com.deas.data.model.Categories


interface ApiHelper {

    suspend fun getCategories() : Categories
}