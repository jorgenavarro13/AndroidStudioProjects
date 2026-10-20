package com.example.vmdemo.sesion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class SessionUiState(
    val nombre : String = "",
    val rol: String = "",
    val estancia: String = ""
){
    val esDireccion: Boolean get() = rol == "Dirección"
}

class SessionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState : StateFlow<SessionUiState> = _uiState.asStateFlow()

    fun iniciarSesion(nombre:String, rol:String, estancia:String){
        _uiState.value = SessionUiState(nombre, rol, estancia)
    }
    fun cambiarEstancia(estancia:String){
        _uiState.update{it.copy(estancia=estancia)}
    }
    fun cerrarSesion(){
        _uiState.value = SessionUiState()
    }
}