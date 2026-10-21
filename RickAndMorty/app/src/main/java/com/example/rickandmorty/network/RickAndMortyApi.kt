package com.example.rickandmorty.network

import com.example.rickandmorty.model.Personaje
import com.example.rickandmorty.model.PersonajesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {


    @GET("character")
    suspend fun obtenerPersonajes(): PersonajesResponse

    @GET("character/{id}")
    suspend fun obtenerPersonaje(@Path ("id") personajeId:Int) : Personaje






}