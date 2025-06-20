package com.example.mq135.presentation.home

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SensorViewModel : ViewModel() {
    private val database = FirebaseDatabase.getInstance("https://mq135-adc0d-default-rtdb.firebaseio.com/").getReference("historial_lecturas")
    var sensorList = mutableStateListOf<SensorData>()
        private set

    init {
        fetchData()
    }

    private fun fetchData() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                sensorList.clear()
                for (child in snapshot.children) {
                    val data = child.getValue(SensorData::class.java)
                    data?.let {
                        val timestampLong = convertirFecha(it.timestamp) // Convierte el String a Long
                        sensorList.add(it.copy(timestamp = it.timestamp)) // Guarda el String original
                    }
                }
                sensorList.sortByDescending { convertirFecha(it.timestamp) } // Ordena por fecha más reciente
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseError", "Error al obtener datos: ${error.message}")
            }
        })
    }
}
