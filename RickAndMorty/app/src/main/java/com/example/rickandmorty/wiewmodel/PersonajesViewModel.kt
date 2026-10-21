package com.example.rickandmorty.wiewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.PersonajeRepository
import com.example.rickandmorty.model.Personaje
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
class PersonajesViewModel(
    private val repo: PersonajeRepository = PersonajeRepository()
) : ViewModel() {
    // La lista empieza vacía: la pantalla muestra un indicador de carga.
    private val _personajes = MutableStateFlow<List<Personaje>>(emptyList())
    val personajes: StateFlow<List<Personaje>> = _personajes.asStateFlow()

    // El detalle empieza en null: también se ve el indicador de carga.
    private val _seleccionado = MutableStateFlow<Personaje?>(null)
    val seleccionado: StateFlow<Personaje?> = _seleccionado.asStateFlow()

    init {
        cargarPersonajes()
    }

    fun cargarPersonajes() {
        viewModelScope.launch {
            try {
                _personajes.value = repo.obtenerLista()
            } catch (e: IOException) {
                Log.e("API", "Sin conexión", e)
            } catch (e: HttpException) {
                Log.e("API", "El servidor respondió ${e.code()}", e)
            }
        }
    }

    fun cargarDetalle(id: Int) {
// Si ya tenemos ese personaje (por ejemplo, al girar la pantalla),
// no lo pedimos otra vez.
        if (_seleccionado.value?.id == id) return
        viewModelScope.launch {
// Mientras llega, se ve el indicador de carga.
            _seleccionado.value = null
            try {
                _seleccionado.value = repo.obtenerDetalle(id)
            } catch (e: IOException) {
                Log.e("API", "Sin conexión", e)
            } catch (e: HttpException) {
                Log.e("API", "El servidor respondió ${e.code()}", e)
            }
        }
    }
}