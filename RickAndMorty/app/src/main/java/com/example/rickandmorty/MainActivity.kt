package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.navigation.ScreenNames
import com.example.rickandmorty.screens.DetalleScreen
import com.example.rickandmorty.screens.ListaScreen
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import com.example.rickandmorty.wiewmodel.PersonajesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainNavigation(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainNavigation(modifier: Modifier = Modifier) {
    val nav = rememberNavController()

    val vm : PersonajesViewModel = viewModel()
    val personajes by vm.personajes.collectAsStateWithLifecycle()

    NavHost(
        navController = nav,
        startDestination = ScreenNames.Lista.route,
        modifier = modifier
    ) {
        composable(ScreenNames.Lista.route) {
            ListaScreen(
                personajes = personajes,
                onClick = { p -> nav.navigate(ScreenNames.Detalle.createRoute(p.id)) }
            )
        }

        composable(
            route = ScreenNames.Detalle.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            LaunchedEffect(id) {
                vm.cargarDetalle(id)
            }

            val personaje by vm.seleccionado.collectAsStateWithLifecycle()
            DetalleScreen(personaje = personaje)
        }
    }
}
