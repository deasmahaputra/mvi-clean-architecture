package com.deas.mylibrary


import app.cash.turbine.test
import com.deas.core.base.DataState
import com.deas.domain.usecase.CategoryUseCase
import com.deas.mylibrary.domain.model.Categories
import com.deas.mylibrary.mapper.CategoryFeatureMapper
import com.deas.mylibrary.presentation.contract.HomeContract
import com.deas.mylibrary.presentation.viewmodel.CategoryViewModel
import com.deas.mylibrary.utils.MainCoroutineRule
import com.deas.mylibrary.utils.TestDataGenerator
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class CategoryViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @MockK
    private lateinit var categoryUseCase: CategoryUseCase

    private val mapper = CategoryFeatureMapper()

    private lateinit var categoryViewModel: CategoryViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        categoryViewModel = CategoryViewModel(categoryUseCase, mapper)
    }

    @Test
    fun `test fetch category item and then success`() = runTest {
        val categoryItem = TestDataGenerator.setCategoryData()
        val categoryFlow = flowOf(DataState.Success(categoryItem))
        val categoryMutableStateFlow = MutableStateFlow(Categories())
        categoryMutableStateFlow.emit(mapper.from(categoryItem))

        coEvery { categoryUseCase.execute(any()) } returns categoryFlow

        categoryViewModel.uiState.test {
            categoryViewModel.setIntent(HomeContract.Intent.GetCategories)

            Truth.assertThat(expectItem().equals(
                HomeContract.ScreenState.Categories(HomeContract.CategoryState.Idlee)))

            Truth.assertThat(expectItem().equals(
                HomeContract.ScreenState.Categories(HomeContract.CategoryState.Loading)))

            val expected = expectItem()
            val expectedDt = (expected as HomeContract.ScreenState.Categories).categoryState
            val expectedData = (expectedDt as HomeContract.CategoryState.Success).categories

            Truth.assertThat(expectItem().equals(
                HomeContract.ScreenState.Categories(HomeContract.CategoryState.Success(
                categoryMutableStateFlow))))

            Truth.assertThat(expectedData).isIn(listOf(mapper.from(categoryItem)))

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { categoryUseCase.execute(any()) }
    }

    @Test
    fun `fetch category item and then fail`() = runTest {
        val categoryErrorFlow = flowOf(DataState.Error(Exception("Error data")))

        coEvery { categoryUseCase.execute(any()) } returns categoryErrorFlow

        categoryViewModel.uiState.test {

            categoryViewModel.setIntent(HomeContract.Intent.GetCategories)

            Truth.assertThat(expectItem() == HomeContract.ScreenState.Categories(HomeContract.CategoryState.Idlee))

            Truth.assertThat(expectItem() == HomeContract.ScreenState.Categories(HomeContract.CategoryState.Loading))

            cancelAndIgnoreRemainingEvents()
        }

        categoryViewModel.effect.test{
            val expected = expectItem()
            val expectedData = (expected as HomeContract.SideEffect.ShowError).message

            Truth.assertThat(expected).isEqualTo(HomeContract.SideEffect.ShowError("Error data"))
            Truth.assertThat(expectedData).isEqualTo("Error data")

            cancelAndIgnoreRemainingEvents()
        }

        coVerify { categoryUseCase.execute(any()) }
    }

}