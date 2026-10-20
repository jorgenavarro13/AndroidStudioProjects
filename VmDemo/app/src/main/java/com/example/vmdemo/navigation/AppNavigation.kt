package com.example.vmdemo.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vmdemo.sesion.SessionViewModel
import com.example.vmdemo.ui.screens.InicioScreen
import com.example.vmdemo.ui.screens.LoginScreen
import com.example.vmdemo.ui.screens.PerfilScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val sessionVm : SessionViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rutas.LOGIN) {

        composable(Rutas.LOGIN) {
            LoginScreen(
                onEntrar = { nombre, rol, estancia ->
                    sessionVm.iniciarSesion(nombre, rol,estancia)
                    navController.navigate(Rutas.INICIO)
                }
            )
        }

        composable(Rutas.INICIO) {
            InicioScreen(
                sessionVm= sessionVm,
                onIrAPerfil = { navController.navigate(Rutas.PERFIL) },
                onRegresar = { navController.popBackStack() }
            )
        }

        composable(Rutas.PERFIL) {
            PerfilScreen(
                sessionVm = sessionVm,
                onRegresar = { navController.popBackStack() },
                onCerrarSesion = {
                    navController.navigate(Rutas.LOGIN) {
                        popUpTo(navController.graph.id) { inclusive = true }
                    }
                }
            )
        }
    }
}