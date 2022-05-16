package com.deas.local.database

import androidx.room.TypeConverter
import com.deas.local.model.CategoriesLocalModel
import com.google.gson.Gson

class Convectors {

    private val gson = Gson()

    @TypeConverter
    fun listToContent(value: List<CategoriesLocalModel.ContentItem>) = gson.toJson(value)

    @TypeConverter
    fun jsonToContent(value : String) = gson.fromJson(value, Array<CategoriesLocalModel.ContentItem>::class.java).toList()
}