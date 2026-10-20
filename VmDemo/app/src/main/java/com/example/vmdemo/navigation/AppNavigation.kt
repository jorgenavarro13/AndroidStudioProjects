package com.example.vmdemo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vmdemo.ui.screens.InicioScreen
import com.example.vmdemo.ui.screens.LoginScreen
import com.example.vmdemo.ui.screens.PerfilScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // PASO 4: aquí crearás el SesionViewModel (uno solo para toda la app).

    NavHost(navController = navController, startDestination = Rutas.LOGIN) {

        composable(Rutas.LOGIN) {
            LoginScreen(
                onEntrar = { nombre, rol, estancia ->
                    // PASO 2 (el problema): Login sí conoce nombre, rol y estancia...
                    // ...pero no hay un lugar compartido donde guardarlos.
                    // Por ahora solo navegamos y esos datos se pierden.
                    navController.navigate(Rutas.INICIO)
                }
            )
        }

        composable(Rutas.INICIO) {
            InicioScreen(
                onIrAPerfil = { navController.navigate(Rutas.PERFIL) },
                onRegresar = { navController.popBackStack() }
            )
        }

        composable(Rutas.PERFIL) {
            PerfilScreen(
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