package com.example.nicolaspuebla_proyecto_final_android.ui.screens.mapScreen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.nicolaspuebla_proyecto_final_android.utils.LocationChoosingInfo
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * ViewModel para la pantalla de mapa que gestiona la búsqueda de lugares y selección de posición.
 *
 * @param context Contexto de la aplicación para inicializar el cliente de Places.
 */
@HiltViewModel
class MapScreenViewModel @Inject constructor(
    @ApplicationContext context: Context
): ViewModel() {

    private val placesClient = Places.createClient(context)
    var searchQuery by mutableStateOf("")
    var predictions = mutableStateListOf<AutocompletePrediction>()
    var showSuggestions by mutableStateOf(false)

    /**
     * Realiza una búsqueda de lugares basándose en la consulta proporcionada.
     *
     * @param query Texto de búsqueda introducido por el usuario.
     */
    fun searchPlaces(query: String) {
        if (query.isEmpty()) {
            predictions.clear()
            showSuggestions = false
            return
        }

        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(query)
            .build()

        placesClient.findAutocompletePredictions(request)
            .addOnSuccessListener { response ->
                predictions.clear()
                predictions.addAll(response.autocompletePredictions)
                showSuggestions = true
            }
            .addOnFailureListener {
                predictions.clear()
                showSuggestions = false
            }
    }

    /**
     * Selecciona un lugar usando su placeId y actualiza la posición seleccionada en el mapa.
     *
     * @param placeId Identificador del lugar seleccionado.
     */
    fun selectPlace(placeId: String) {
        val fields = listOf(Place.Field.LAT_LNG)
        val request = FetchPlaceRequest.newInstance(placeId, fields)

        placesClient.fetchPlace(request)
            .addOnSuccessListener { response ->
                response.place.latLng?.let {
                    LocationChoosingInfo.setSelectedMapPosition(it)
                    showSuggestions = false
                    searchQuery = ""
                }
            }
    }
}