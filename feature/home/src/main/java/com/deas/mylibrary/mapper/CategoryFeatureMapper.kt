package com.deas.mylibrary.mapper

import com.deas.data.repository.Mapper
import com.deas.domain.entity.CategoryEntity
import com.deas.mylibrary.domain.model.Categories
import javax.inject.Inject

class CategoryFeatureMapper @Inject constructor() : Mapper<CategoryEntity, Categories> {
    override fun from(i: CategoryEntity?): Categories {
        val content : MutableList<Categories.ContentItem> = mutableListOf()
        i?.content?.forEach {
            val item = Categories.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return Categories(
            id = i?.id,
            content = content
        )
    }

    override fun to(o: Categories?): CategoryEntity {
        val content : MutableList<CategoryEntity.ContentItem> = mutableListOf()
        o?.content?.forEach {
            val item = CategoryEntity.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return CategoryEntity(
            id = o?.id,
            content = content
        )
    }

}