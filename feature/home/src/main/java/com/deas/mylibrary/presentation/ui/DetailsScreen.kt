package com.deas.mylibrary.presentation.ui

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.deas.core.uikit.AvatarImageWithCoil
import com.deas.core.uikit.ImageWithCoil
import com.deas.mylibrary.domain.model.Categories

@Composable
internal fun DetailsScreen(
    navController: NavController,
    category: Categories.ContentItem?
) {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(10.dp)) {

        ImageWithCoil(category?.category_url_image?:"")

        Text(text = "Kategori ${category?.category_name}",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
            )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = { navController.popBackStack() }, colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Kembali", fontSize = 16.sp, color = Color.White)
        }

    }
}