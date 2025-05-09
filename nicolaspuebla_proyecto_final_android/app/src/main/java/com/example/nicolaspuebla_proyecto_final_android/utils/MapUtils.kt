package com.example.nicolaspuebla_proyecto_final_android.utils

object MapUtils {

    fun generateStaticMapUrl(latitude: Double, longitude: Double): String {
        println(">>>>>>>>>>>>generando imagen del mapa")
    return "https://maps.googleapis.com/maps/api/staticmap?" +
        "center=$latitude,$longitude" +
        "&zoom=15" +
        "&size=400x200" +
        "&markers=color:red%7Clabel:A%7C$latitude,$longitude" +
        "&key=AIzaSyAyC3hxTl_mDCifxGrWTCT2i8De9U2piC4"
    }
}