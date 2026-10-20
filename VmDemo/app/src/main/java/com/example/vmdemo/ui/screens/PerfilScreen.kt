package com.example.vmdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vmdemo.data.Catalogos
import com.example.vmdemo.ui.components.OpcionesRadio

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    onRegresar: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    // PASO 2 (el problema): Perfil también tiene su propio estado.
    // Si cambias la estancia aquí, Inicio nunca se entera.
    val nombre by remember { mutableStateOf("") }
    val rol by remember { mutableStateOf("") }
    var estancia by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil") },
                navigationIcon = {
                    IconButton(onClick = onRegresar) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = nombre.ifBlank { "(sin nombre)" },
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = rol.ifBlank { "(sin rol)" },
                style = MaterialTheme.typography.titleMedium
            )
            Text("Estancia actual: ${estancia.ifBlank { "(sin estancia)" }}")
            OpcionesRadio(
                titulo = "Cambiar estancia",
                opciones = Catalogos.ESTANCIAS,
                seleccion = estancia,
                onSeleccion = { estancia = it }
            )
            OutlinedButton(
                onClick = onCerrarSesion,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}