package com.deas.data.utils

import com.deas.data.model.CategoriesDto

class TestDataGenerator {

    companion object{

        fun setCategoryData() : CategoriesDto{
            val contents : MutableList<CategoriesDto.ContentItem> = mutableListOf()
            val content = CategoriesDto.ContentItem(
                category_name = "Sabun",
                category_url_image = "image"
            )
            contents.add(content)
            return CategoriesDto(id = 1, content = contents)
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