package com.example.mq135.presentation.home

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class SensorData(
    val CO2: Float = 0.0f,
    val timestamp: String = ""
)
fun convertirFecha(fechaStr: String): Long {
    return try {
        val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        formatter.parse(fechaStr)?.time ?: 0L
    } catch (e: ParseException) {
        Log.e("FechaError", "Error al convertir fecha: ${e.message}")
        0L
    }
}