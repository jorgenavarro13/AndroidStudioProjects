package com.example.vmdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
fun LoginScreen(
    onEntrar: (nombre: String, rol: String, estancia: String) -> Unit
) {
    // Estado del formulario: solo le importa a esta pantalla, así que remember está bien aquí.
    var nombre by remember { mutableStateOf("") }
    var rol by remember { mutableStateOf(Catalogos.ROLES.first()) }
    var estancia by remember { mutableStateOf(Catalogos.ESTANCIAS.first()) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Iniciar sesión") }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OpcionesRadio(
                titulo = "Rol",
                opciones = Catalogos.ROLES,
                seleccion = rol,
                onSeleccion = { rol = it }
            )
            OpcionesRadio(
                titulo = "Estancia",
                opciones = Catalogos.ESTANCIAS,
                seleccion = estancia,
                onSeleccion = { estancia = it }
            )
            Button(
                onClick = { onEntrar(nombre.trim(), rol, estancia) },
                enabled = nombre.isNotBlank(),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Entrar")
            }
        }
    }
}