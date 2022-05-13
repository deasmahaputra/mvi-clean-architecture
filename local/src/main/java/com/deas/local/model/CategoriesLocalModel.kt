package com.deas.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoriesLocalModel(
    @PrimaryKey
    val id: Int,
    val name: String
)