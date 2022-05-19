package com.deas.local.source


import com.deas.data.model.CategoriesDto
import com.deas.data.repository.LocalDataSource
import com.deas.data.repository.Mapper
import com.deas.local.dao.RoomDao
import com.deas.local.model.CategoriesLocalModel
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val roomDao: RoomDao,
    private val mapper : Mapper<CategoriesLocalModel, CategoriesDto>
) : LocalDataSource {

    override suspend fun addItem(category: CategoriesDto) {
        val categoryLocalModel = mapper.to(category)
        return roomDao.addContentCategory(category = categoryLocalModel)
    }

    override suspend fun getItems(): List<CategoriesDto> {
        val categoryLocalList = roomDao.getContentCategory()
        return mapper.fromList(categoryLocalList)
    }

    override suspend fun updateItem(category: CategoriesDto) {}

    override suspend fun clearCachedItems() {
        return roomDao.clearCachedWeatherItems()
    }

}