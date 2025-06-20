package com.example.mq135.presentation.grafica

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mq135.presentation.home.SensorViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.ChartEntry
import com.patrykandpatrick.vico.core.entry.FloatEntry
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.entryOf
import com.patrykandpatrick.vico.compose.axis.horizontal.rememberBottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.rememberStartAxis
import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatrick.vico.compose.chart.scroll.rememberChartScrollState
import com.patrykandpatrick.vico.compose.chart.scroll.ChartScrollSpec

@Composable
fun GraficaScreen(viewModel: SensorViewModel = viewModel(), navHostController: NavHostController) {
    val data = viewModel.sensorList

    // Convertimos los datos a puntos (x = índice, y = CO2)
    // Convertimos los datos a puntos (x = índice, y = CO2)
    val entries: List<ChartEntry> = data.mapIndexed { index, lectura ->
        FloatEntry(index.toFloat(), lectura.CO2)
    }

    val model = entryModelOf(
        *data.mapIndexed { index, lectura ->
            index.toFloat() to lectura.CO2
        }.toTypedArray()
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (dragAmount < 100) { // Swipe hacia la izquierda
                        navHostController.navigate("home")
                    }
                }
            }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Gráfica de CO₂", style = MaterialTheme.typography.headlineMedium)

            // Este es el componente correcto de Vico
            com.patrykandpatrick.vico.compose.chart.Chart(
                chart = lineChart(),
                model = model
            )
        }
    }
}