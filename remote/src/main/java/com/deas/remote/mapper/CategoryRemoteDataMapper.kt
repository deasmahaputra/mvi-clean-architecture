package com.deas.remote.mapper

import com.deas.data.model.CategoriesDto
import com.deas.data.repository.Mapper
import com.deas.remote.model.CategoryRemote
import javax.inject.Inject

class CategoryRemoteDataMapper @Inject constructor() : Mapper<CategoryRemote, CategoriesDto>{

    override fun from(i: CategoryRemote?): CategoriesDto {
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

    override fun to(o: CategoriesDto?): CategoryRemote {
        val content : MutableList<CategoryRemote.ContentItem> = mutableListOf()
        o?.content?.forEach {
            val item = CategoryRemote.ContentItem(
                category_name = it.category_name,
                category_url_image = it.category_url_image
            )
            content.add(item)
        }
        return CategoryRemote(
            id = o?.id,
            content = content
        )
    }


}