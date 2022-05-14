package com.deas.local.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "categories")
@Parcelize
data class CategoriesLocalModel(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "content") val content: List<ContentItem>? = null
) : Parcelable {

    @Parcelize
    data class ContentItem(
        @ColumnInfo(name = "category_name") val category_name: String? = null,
        @ColumnInfo(name = "category_url_image") val category_url_image: String? = null
    ) : Parcelable
}







