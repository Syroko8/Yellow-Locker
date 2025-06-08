package com.example.nicolaspuebla_proyecto_final_android.ui.screens.mapScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.utils.LocationChoosingInfo
import com.example.nicolaspuebla_proyecto_final_android.utils.MapAction
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

/**
 * Pantalla de mapa interactivo para la selección de localizaciones.
 *
 * @param viewModel ViewModel que maneja el estado y lógica para la búsqueda y selección de lugares.
 */
@Composable
fun MapScreen(viewModel: MapScreenViewModel = hiltViewModel()) {
    val selectedMapPosition by LocationChoosingInfo.selectedMapPosition.collectAsState()
    val event by LocationChoosingInfo.event.collectAsState()
    val eventCreationLocation = LocationChoosingInfo.eventCreationInfo.value?.location

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            if(LocationChoosingInfo.action.value == MapAction.ChangeOpponent)
                LatLng(event?.latitude ?:40.41649453615158, event?.longitude ?: -3.7015116455504833) else
                if(eventCreationLocation != null) LatLng(eventCreationLocation.latitude, eventCreationLocation.longitude) else
                    LatLng(40.41649453615158, -3.7015116455504833)
            , 12f
        )
    }

    LaunchedEffect(Unit) {
        LocationChoosingInfo.setSelectedMapPosition(
            if(LocationChoosingInfo.action.value == MapAction.ChangeOpponent)
            LatLng(event?.latitude ?:40.41649453615158, event?.longitude ?: -3.7015116455504833) else
            if(eventCreationLocation != null) LatLng(eventCreationLocation.latitude, eventCreationLocation.longitude) else
                LatLng(40.41649453615158, -3.7015116455504833)
        )
    }

    LaunchedEffect(selectedMapPosition) {
        selectedMapPosition?.let {
            cameraPositionState.animate(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(it, 12f)
                ),
                durationMs = 1000
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                compassEnabled = true,
                myLocationButtonEnabled = false,
                zoomGesturesEnabled = true,
                zoomControlsEnabled = false
            ),
            properties = MapProperties(mapType = MapType.NORMAL),
            onMapClick = {
                LocationChoosingInfo.setSelectedMapPosition(it)
            }
        ) {
            Marker(
                state = MarkerState(
                    position = selectedMapPosition ?: if(LocationChoosingInfo.action.value == MapAction.ChangeOpponent)
                        LatLng(event?.latitude ?:40.41649453615158, event?.longitude ?: -3.7015116455504833) else
                        if(eventCreationLocation != null) LatLng(eventCreationLocation.latitude, eventCreationLocation.longitude) else
                            LatLng(40.41649453615158, -3.7015116455504833)
                )
            )
        }
        SearchBar(viewModel)
        SuggestionsList(viewModel)
    }
}

/**
 * Barra de búsqueda de lugares.
 *
 * @param viewModel ViewModel que maneja el estado y acciones de búsqueda.
 */
@Composable
private fun SearchBar(viewModel: MapScreenViewModel) {
    TextField(
        value = viewModel.searchQuery,
        onValueChange = {
            viewModel.searchQuery = it
            viewModel.searchPlaces(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White, RoundedCornerShape(8.dp)),
        placeholder = { Text(stringResource(R.string.search_location)) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
        ),
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = stringResource(R.string.sear_icon_description)) }
    )
}

/**
 * Lista desplegable que muestra sugerencias de lugares basadas en el texto de búsqueda.
 *
 * @param viewModel ViewModel que contiene la lista de predicciones y controla su visibilidad.
 */
@Composable
private fun SuggestionsList(viewModel: MapScreenViewModel) {

    if (viewModel.showSuggestions) {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .offset(y = 60.dp)
                .fillMaxWidth()
                .heightIn(max = 200.dp)
                .background(Color.White, RoundedCornerShape(8.dp))
        ) {
            items(viewModel.predictions) { prediction ->
                Text(
                    text = prediction.getFullText(null).toString(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.selectPlace(prediction.placeId)
                        }
                        .padding(16.dp),
                    color = Color.Black
                )
                HorizontalDivider()
            }
        }
    }
}