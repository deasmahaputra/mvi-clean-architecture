package com.deas.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.deas.local.dao.RoomDao
import com.deas.local.model.CategoriesLocalModel


@Database(entities = [CategoriesLocalModel::class], version = 1, exportSchema = false)
@TypeConverters(Convectors::class)
abstract class AppDatabase : RoomDatabase(){

    abstract fun roomDao() : RoomDao

}