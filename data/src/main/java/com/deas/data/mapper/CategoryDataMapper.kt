package com.deas.data.mapper

import com.deas.data.model.CategoriesDto
import com.deas.data.repository.Mapper
import com.deas.domain.entity.CategoryEntity
import javax.inject.Inject

class CategoryDataMapper @Inject constructor() : Mapper<CategoriesDto, CategoryEntity> {
    override fun from(i: CategoriesDto?): CategoryEntity {
        val content : MutableList<CategoryEntity.ContentItem> = mutableListOf()
        i?.content?.forEach {
            val item = CategoryEntity.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return CategoryEntity(
            id = i?.id,
            content = content

        )
    }

    override fun to(o: CategoryEntity?): CategoriesDto {
        val content : MutableList<CategoriesDto.ContentItem> = mutableListOf()
        o?.content?.forEach {
            val item = CategoriesDto.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return CategoriesDto(
            id = o?.id,
            content = content
        )
    }
}