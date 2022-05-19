package com.deas.mylibrary.common.navigation

import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.deas.mylibrary.domain.model.Categories
import com.deas.mylibrary.presentation.ui.DetailsScreen
import com.deas.navigation.Screen
import com.deas.navigation.base.NavComposableModule
import com.google.gson.Gson

val detailScreenNavModule = NavComposableModule { navHost, navController ->
    navHost.apply {
        composable(
            route = Screen.Detail.params + "/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                    defaultValue = "category"
                    nullable = false
                }
            )
        ) { entry ->
            val category= entry.arguments?.getString("category")
            val categoryData = Gson().fromJson(category, Categories.ContentItem::class.java)
            DetailsScreen(
                navController = navController,
                category = categoryData,
            )
        }
    }
}