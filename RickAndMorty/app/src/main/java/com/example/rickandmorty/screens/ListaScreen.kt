package com.example.rickandmorty.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.rickandmorty.components.PersonajeCard
import com.example.rickandmorty.model.Personaje

@Composable
fun ListaScreen(
    personajes: List<Personaje>,
    onClick: (Personaje) -> Unit,
    modifier: Modifier = Modifier
) {
    // Mientras la lista esté vacía, mostramos un indicador de carga.
    if (personajes.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    LazyColumn(modifier = modifier) {
        items(personajes, key = { it.id }) { personaje ->
            PersonajeCard(personaje = personaje, onClick = onClick)
        }
    }
}
