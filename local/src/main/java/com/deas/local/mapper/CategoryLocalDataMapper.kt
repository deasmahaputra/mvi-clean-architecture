package com.deas.local.mapper

import com.deas.data.model.CategoriesDto
import com.deas.data.repository.Mapper
import com.deas.local.model.CategoriesLocalModel
import javax.inject.Inject

class CategoryLocalDataMapper @Inject constructor() : Mapper<CategoriesLocalModel, CategoriesDto> {

    override fun from(i: CategoriesLocalModel?): CategoriesDto {
        val content : MutableList<CategoriesDto.ContentItem> = mutableListOf()
        i?.content?.forEach {
            val item = CategoriesDto.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return CategoriesDto(
            id = i?.id,
            content = content
        )
    }

    override fun to(o: CategoriesDto?): CategoriesLocalModel {
        val content : MutableList<CategoriesLocalModel.ContentItem> = mutableListOf()
        o?.content?.forEach {
            val item = CategoriesLocalModel.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return CategoriesLocalModel(
            id = o?.id,
            content = content
        )
    }

}