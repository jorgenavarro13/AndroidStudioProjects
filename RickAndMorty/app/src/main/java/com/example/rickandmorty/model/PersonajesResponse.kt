package com.example.rickandmorty.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonajesResponse(
    @SerialName("results")
    val results: List<Personaje>
)