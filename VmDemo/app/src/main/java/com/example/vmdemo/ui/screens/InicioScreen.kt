package com.example.vmdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    onIrAPerfil: () -> Unit = {},
    onRegresar: () -> Unit = {}
) {
    // PASO 2 (el problema): Inicio tiene SU PROPIO estado.
    // No sabe qué se capturó en Login, así que todo empieza vacío.
    val nombre by remember { mutableStateOf("") }
    val rol by remember { mutableStateOf("") }
    val estancia by remember { mutableStateOf("") }

    // La regla del rol escrita aquí... y otra vez en cada pantalla que la necesite.
    val esDireccion = rol == "Dirección"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inicio") },
                navigationIcon = {
                    IconButton(onClick = onRegresar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                },
                actions = { TextButton(onClick = onIrAPerfil) { Text("Perfil") } }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Hola, ${nombre.ifBlank { "(sin nombre)" }}",
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = estancia.ifBlank { "(sin estancia)" },
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text("Entradas hoy: 12")
                    Text("Faltas: 1")
                }
            }
            if (esDireccion) {
                Button(onClick = { /* ver reportes */ }) {
                    Text("Reportes de todas las estancias")
                }
            }
        }
    }
}