package com.example.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.rickandmorty.model.getPersonajes
import com.example.rickandmorty.navigation.ScreenNames
import com.example.rickandmorty.screens.DetalleScreen
import com.example.rickandmorty.screens.ListaScreen
import com.example.rickandmorty.ui.theme.RickAndMortyTheme

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
    val personajes = remember { getPersonajes() }

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
            val personaje = personajes.firstOrNull { it.id == id }
            DetalleScreen(personaje = personaje)
        }
    }
}
