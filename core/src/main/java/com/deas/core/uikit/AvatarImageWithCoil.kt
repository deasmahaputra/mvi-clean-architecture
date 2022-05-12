package com.deas.core.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation


@ExperimentalCoilApi
@Preview
@Composable
fun AvatarImageWithCoil(url:String = "https://www.w3schools.com/howto/img_avatar.png") {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            transformations(
                CircleCropTransformation()
            )
        })
    Image(
        painter = painter,
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(2.dp, Color.DarkGray, CircleShape)
    )
}