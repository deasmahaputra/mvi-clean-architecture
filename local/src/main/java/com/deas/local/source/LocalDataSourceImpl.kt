package com.deas.local.source


import com.deas.data.model.Categories
import com.deas.data.repository.LocalDataSource
import com.deas.local.dao.RoomDao
import com.deas.local.model.CategoriesLocalModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val roomDao: RoomDao
    ) : LocalDataSource {

    override suspend fun addItem(category: Categories) {
//        val categoryLocalModel = categoryMapper.to(category)
//        return roomDao.addContentCategory(category = categoryLocalModel)
    }

    override suspend fun getItems(): List<Categories> {
//        val categoryLocalModel = roomDao.getContentCategory()
//        return categoryMapper.fromList(categoryLocalModel)
        return listOf()
    }

    override suspend fun updateItem(category: Categories) {

    }

}