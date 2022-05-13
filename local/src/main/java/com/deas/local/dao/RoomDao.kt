package com.deas.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.deas.local.model.CategoriesLocalModel

@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addContentCategory(category : CategoriesLocalModel)

    @Query("SELECT * FROM categories")
    suspend fun getContentCategory() : List<CategoriesLocalModel>

}