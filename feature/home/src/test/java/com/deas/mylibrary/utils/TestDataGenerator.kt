package com.deas.mylibrary.utils

import com.deas.data.model.CategoriesDto
import com.deas.domain.entity.CategoryEntity
import com.deas.mylibrary.domain.model.Categories


class TestDataGenerator {

    companion object{
        fun setCategoryData() : CategoryEntity {
            val contents : MutableList<CategoryEntity.ContentItem> = mutableListOf()
            val content = CategoryEntity.ContentItem(
                category_name = "Sabun",
                category_url_image = "image"
            )
            contents.add(content)
            return CategoryEntity(id = 1, content = contents)
        }

        fun setCategoryListData(): MutableList<CategoriesDto> {
            val contents: MutableList<CategoriesDto.ContentItem> = mutableListOf()
            val content = CategoriesDto.ContentItem(
                category_name = "Sabun1",
                category_url_image = "image1"
            )
            contents.add(content)
            return mutableListOf(CategoriesDto(id = 1, content = contents))
        }

    }
}