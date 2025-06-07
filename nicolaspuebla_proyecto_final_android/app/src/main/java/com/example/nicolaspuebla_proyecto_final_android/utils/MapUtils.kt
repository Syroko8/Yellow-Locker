package com.example.nicolaspuebla_proyecto_final_android.utils

/**
 * Objeto empleado para obtener vistas previas de coordenadas en el mapa.
 */
object MapUtils {

    /**
     * Función que realiza la petición para obtener la vista previa.
     *
     * @param latitude Latitud de la posición de la que se desea recibir la vista previa.
     * @param longitude Longitud de la posición de la que se desea recibir la vista previa.
     */
    fun generateStaticMapUrl(latitude: Double, longitude: Double): String {
    return "https://maps.googleapis.com/maps/api/staticmap?" +
        "center=$latitude,$longitude" +
        "&zoom=13" +
        "&size=400x200" +
        "&markers=color:red%7Clabel:A%7C$latitude,$longitude" +
        "&key=AIzaSyAyC3hxTl_mDCifxGrWTCT2i8De9U2piC4"
    }
}