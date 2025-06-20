package com.example.mq135

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mq135.presentation.grafica.GraficaScreen
import com.example.mq135.presentation.home.HomeScreen
import com.example.mq135.presentation.home.SensorViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val viewModel: SensorViewModel = viewModel()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }

        composable("grafica") {
            GraficaScreen(viewModel, navController)
        }
    }
}
