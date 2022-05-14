package com.deas.domain.entity

data class CategoryEntity(
    val id: Int? = null,
    val number: Int? = null,
    val last: Boolean? = null,
    val number_of_elements: Int? = null,
    val size: Int? = null,
    val total_pages: Int? = null,
    val pageable: Pageable? = null,
    val sort: Sort? = null,
    val content: List<ContentItem>? = null,
    val first: Boolean? = null,
    val total_elements: Int? = null,
    val empty: Boolean? = null
){

    data class ContentItem(
        val is_twenty_one: Boolean? = null,
        val title_visible: Boolean? = null,
        val category_name: String? = null,
        val category_id: Int? = null,
        val category_position: Int? = null,
        val category_url_image: String? = null,
        val category_active: Boolean? = null,
        val is_product_exist: Boolean? = null,
        val category_childs: List<CategoryChildsItem?>? = null
    ){
        data class CategoryChildsItem(
            val is_twenty_one: Boolean? = null,
            val title_visible: Boolean? = null,
            val category_name: String? = null,
            val category_id: Int? = null,
            val category_position: Int? = null,
            val category_url_image: Any? = null,
            val category_active: Boolean? = null,
            val is_product_exist: Boolean? = null
        )
    }
}



data class Pageable(
    val paged: Boolean? = null,
    val page_number: Int? = null,
    val offset: Int? = null,
    val page_size: Int? = null,
    val unpaged: Boolean? = null,
    val sort: Sort? = null
)

data class Sort(
    val unsorted: Boolean? = null,
    val sorted: Boolean? = null,
    val empty: Boolean? = null
)





