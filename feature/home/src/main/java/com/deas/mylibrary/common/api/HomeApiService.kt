package com.deas.mylibrary.common.api

import com.deas.mylibrary.domain.model.Categories
import retrofit2.http.GET

interface HomeApiService {

    @GET("category")
    suspend fun getCategories() : Categories
}