package com.deas.mvi.di

import com.deas.data.mapper.CategoryDataMapper
import com.deas.data.model.CategoriesDto
import com.deas.data.repository.Mapper
import com.deas.domain.entity.CategoryEntity
import com.deas.local.mapper.CategoryLocalDataMapper
import com.deas.local.model.CategoriesLocalModel
import com.deas.mylibrary.domain.model.Categories
import com.deas.mylibrary.mapper.CategoryFeatureMapper
import com.deas.remote.mapper.CategoryRemoteDataMapper
import com.deas.remote.model.CategoryRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    abstract fun bindsCategoryLocalDataMapper(mapper : CategoryLocalDataMapper) : Mapper<CategoriesLocalModel, CategoriesDto>

    @Binds
    abstract fun bindsCategoryDataDomainMapper(mapper : CategoryDataMapper) : Mapper<CategoriesDto, CategoryEntity>

    @Binds
    abstract fun bindsCategoryUiMapper(mapper: CategoryFeatureMapper) : Mapper<CategoryEntity, Categories>

    @Binds
    abstract fun bindsCategoryRemoteMapper(mapper: CategoryRemoteDataMapper) : Mapper<CategoryRemote, CategoriesDto>
}