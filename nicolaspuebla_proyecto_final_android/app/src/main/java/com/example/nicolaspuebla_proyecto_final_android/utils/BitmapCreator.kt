// ImageUtils.kt
package com.example.nicolaspuebla_proyecto_final_android.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import android.graphics.Bitmap
import androidx.compose.ui.unit.dp

@Composable // Añade decorador Composable
fun ImageVector.toImageBitmap(sizeDp: Int = 24): ImageBitmap {

    val density = LocalDensity.current
    val sizePx = with(density) { sizeDp.dp.roundToPx() }

    val bitmap = Bitmap.createBitmap(sizePx, sizePx, Bitmap.Config.ARGB_8888)

    return bitmap.asImageBitmap()
}