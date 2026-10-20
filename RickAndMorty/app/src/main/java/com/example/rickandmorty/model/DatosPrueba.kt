package com.example.rickandmorty.model

data class Personaje(
    val id: Int,
    val name: String,
    val status: String,   // "Alive", "Dead" o "unknown"
    val species: String,
    val gender: String,
    val image: String     // URL de la imagen
)

fun getPersonajes(): List<Personaje> = listOf(
    Personaje(1, "Rick Sanchez", "Alive", "Human", "Male",
        "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
    Personaje(2, "Morty Smith", "Alive", "Human", "Male",
        "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
    Personaje(3, "Summer Smith", "Alive", "Human", "Female",
        "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
)
