package com.deas.remote.api


import com.deas.remote.model.CategoryRemote
import retrofit2.http.GET

interface ApiService {

    @GET("category")
    suspend fun getCategories() : CategoryRemote
}