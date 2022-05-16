package com.deas.data

import app.cash.turbine.test
import com.deas.core.base.DataState
import com.deas.data.mapper.CategoryDataMapper
import com.deas.data.model.CategoriesDto
import com.deas.data.repository.CategoryRepositoryImpl
import com.deas.data.repository.LocalDataSource
import com.deas.data.repository.RemoteDataSource
import com.deas.data.utils.TestDataGenerator
import com.deas.domain.repository.Repository
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class CategoryRepositoryImplTest {

    @MockK
    private lateinit var localDataSource: LocalDataSource

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private val mapper = CategoryDataMapper()

    private lateinit var repository: Repository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        repository = CategoryRepositoryImpl(remoteDataSource, localDataSource, mapper)
    }

    @Test
    fun `test get posts remote success`() = runBlockingTest {
        val categoryItem = TestDataGenerator.setCategoryData()
        val categories  = TestDataGenerator.setCategoryListData()


        coEvery { remoteDataSource.getCategory() } returns categoryItem
        coEvery { localDataSource.clearCachedItems() } returns any()
        coEvery { localDataSource.addItem(eq(categoryItem)) } returns any()
        coEvery { localDataSource.getItems() } returns categories

        val flow = repository.getCategories()
        flow.test {
            val expected = expectItem()
            val expectedData = (expected as DataState.Success).data
            Truth.assertThat(expected).isInstanceOf(DataState.Success::class.java)
            Truth.assertThat(eq(expectedData.content?.get(0)?.category_name)).isEqualTo(eq(mapper.from(categoryItem).content?.get(0)?.category_name))
            expectComplete()
        }

        coVerify { remoteDataSource.getCategory() }
        coVerify { localDataSource.clearCachedItems() }
        coVerify { localDataSource.addItem(categoryItem) }
    }

    @Test
    fun `test get posts remote fail local success`() = runBlockingTest {
        val categories = TestDataGenerator.setCategoryListData()

        coEvery { remoteDataSource.getCategory() } throws Exception()
        coEvery { localDataSource.getItems() } returns categories

        val flow = repository.getCategories()
        flow.test {
            val expected = expectItem()
            val expectedData = (expected as DataState.Success).data
            Truth.assertThat(expected).isInstanceOf(DataState.Success::class.java)
            Truth.assertThat(eq(expectedData.content?.get(0)?.category_name)).isEqualTo(eq(mapper.from(categories[0]).content?.get(0)?.category_name))
            expectComplete()
        }

        coVerify { remoteDataSource.getCategory() }
        coVerify { localDataSource.getItems() }

    }

    @Test
    fun `test get local fail and remote fail`() = runBlockingTest {
        coEvery { remoteDataSource.getCategory() } throws Exception()
        coEvery { localDataSource.getItems() } throws  Exception()

        val flow = repository.getCategories()
        flow.test {
            Truth.assertThat(expectItem()).isInstanceOf(DataState.Error::class.java)
            expectComplete()
        }

        coVerify { remoteDataSource.getCategory() }
        coVerify { localDataSource.getItems() }
    }

}