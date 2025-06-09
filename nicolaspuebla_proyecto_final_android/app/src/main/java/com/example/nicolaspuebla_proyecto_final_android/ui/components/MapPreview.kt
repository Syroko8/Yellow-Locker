package com.example.nicolaspuebla_proyecto_final_android.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Downloading
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.nicolaspuebla_proyecto_final_android.R

/**
 * Función que muestra la vista previa de una coordenada en el mapa.
 *
 * @param latitude Latud de la coordenada.
 * @param longitude Longitud de la coordenada.
 * @param context Contexto de la vista donde se pintará la imagen o con el que lanzaremos un intent.
 */
@Composable
fun MapPreview(
    latitude: Double,
    longitude: Double,
    context: Context
) {
    val key = stringResource(R.string.google_maps_key)
    println(">>>>>>>>>>>>>>>>>>><<<${key}")
    val staticMapUrl = remember { generateStaticMapUrl(latitude,longitude, key = key) }

    val imageLoader = ImageLoader.Builder(context)
        .crossfade(true)
        .build()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                openGoogleMaps(context, latitude, longitude)
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = staticMapUrl,
            contentDescription = stringResource(R.string.map_preview_description),
            contentScale = ContentScale.Crop,
            imageLoader = imageLoader,
            onLoading = { Icons.Default.Downloading },
            onError = { Icons.Default.Error },
            modifier = Modifier.fillMaxHeight()
        )
    }
}

/**
 * Función que ejecuta la aplicación de Google Maps o lo abre en el navegador.
 *
 * @param context Contexto con el que lanzaremos el intent.
 */
fun openGoogleMaps(context: Context, latitude: Double, longitude: Double) {
    val gmmIntentUri  = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri )
    mapIntent.setPackage("com.google.android.apps.maps")

    // Verificar si Google Maps manejará el intent.
    if (mapIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(mapIntent)
    } else {
        // Si Google Maps no está instalado, abrimos el navegador.
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps?q=$latitude,$longitude")
        )
        context.startActivity(webIntent)
    }
}

/**
 * Función que realiza la petición para obtener la vista previa.
 *
 * @param latitude Latitud de la posición de la que se desea recibir la vista previa.
 * @param longitude Longitud de la posición de la que se desea recibir la vista previa.
 */
fun generateStaticMapUrl(latitude: Double, longitude: Double, key: String): String {
    return "https://maps.googleapis.com/maps/api/staticmap?" +
            "center=$latitude,$longitude" +
            "&zoom=13" +
            "&size=400x200" +
            "&markers=color:red%7Clabel:A%7C$latitude,$longitude" +
            "&key=${key}"
}