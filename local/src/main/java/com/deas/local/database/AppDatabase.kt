package com.deas.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deas.local.dao.RoomDao
import com.deas.local.model.CategoriesLocalModel

@Database(entities = [CategoriesLocalModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun roomDao() : RoomDao

}