package com.deas.mylibrary.presentation.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.deas.core.uikit.AvatarImageWithCoil
import com.deas.core.uikit.ErrorView
import com.deas.core.uikit.LoadingView
import com.deas.mylibrary.domain.model.Categories
import com.deas.mylibrary.presentation.contract.HomeContract
import com.deas.mylibrary.presentation.viewmodel.CategoryViewModel
import com.deas.navigation.Screen
import com.google.gson.Gson
import kotlinx.coroutines.flow.collect

@ExperimentalCoilApi
@Composable
internal fun HomeScreen(navController: NavController, categoryViewModel: CategoryViewModel) {

    when (val screenState = categoryViewModel.uiState.collectAsState().value) {
        is HomeContract.ScreenState.Idle -> {
            categoryViewModel.setIntent(
                intent = HomeContract.Intent.GetCategories
            )
        }
        is HomeContract.ScreenState.Categories -> {
            CategoryStateHandler(
                categoryState = screenState.categoryState,
                navController = navController,
                categoryViewModel = categoryViewModel
            )
        }
        is HomeContract.ScreenState.SideEffect -> {
            SideEffectHandler(effectState = screenState.sideEffect)
        }
    }

}

@Composable
fun SideEffectHandler(effectState: HomeContract.SideEffect) {
    when (effectState) {
        is HomeContract.SideEffect.ShowError -> {
            ErrorView()
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CategoryStateHandler(
    categoryState: HomeContract.CategoryState,
    navController: NavController,
    categoryViewModel: CategoryViewModel
) {
    when (categoryState) {
        is HomeContract.CategoryState.Loading -> LoadingView()
        is HomeContract.CategoryState.Success -> {
            val category = categoryState.categories.collectAsState()
            LoadHomeScreenView(
                navController = navController,
                content = category.value.content?.toMutableList() ?: mutableListOf(),
                categoryViewModel = categoryViewModel
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoilApi
@Composable
fun LoadHomeScreenView(
    navController: NavController,
    content: MutableList<Categories.ContentItem>,
    categoryViewModel: CategoryViewModel
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Astro MVI Architecture", textAlign = TextAlign.Center)
                    }
                }
            )
        },
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(3),
            modifier = Modifier.background(Color.DarkGray)
        ) {
            itemsIndexed(content) { index, item ->
                CategoryCardView(navController = navController, categories = item)
            }
        }

    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CategoryCardView(
    navController: NavController,
    categories: Categories.ContentItem
) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            val dataCategory = Uri.encode(Gson().toJson(categories))
            navController.navigate(
                Screen.Detail.withArgs(
                    dataCategory
                )
            )
        }) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AvatarImageWithCoil(
                url = categories.category_url_image ?: ""
            )
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = categories.category_name ?: "", textAlign = TextAlign.Center)
                }
                Spacer(modifier = Modifier.padding(6.dp))
            }
        }
    }
}

