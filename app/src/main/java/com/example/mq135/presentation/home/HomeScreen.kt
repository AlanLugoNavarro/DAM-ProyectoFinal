package com.example.mq135.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mq135.ui.theme.Black
import com.example.mq135.ui.theme.Blanco
import com.example.mq135.ui.theme.Gray

@Composable
fun HomeScreen(navHostController: NavHostController){
    val viewModel: SensorViewModel = viewModel()
    SensorTable(viewModel, navHostController)
}

@Composable
fun SensorTable(viewModel: SensorViewModel = viewModel(), navHostController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount < -100) { // Swipe hacia la izquierda
                        navHostController.navigate("grafica")
                    }
                }
            }

    ){
        Column(modifier = Modifier.fillMaxSize()
            .background(Brush.verticalGradient(listOf(Gray, Black)))) {
            Text(text = "Historial de mediciones", style = MaterialTheme.typography.headlineMedium, color = Blanco)

            LazyColumn {
                items(viewModel.sensorList) { lectura ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "CO2: ${String.format("%.2f", lectura.CO2)} ppm", modifier = Modifier.weight(1f), color = Blanco)
                        Text(text = "Hora: ${lectura.timestamp}", modifier = Modifier.weight(1f), color = Blanco)
                    }
                    Divider()
                }
            }
        }
    }

}