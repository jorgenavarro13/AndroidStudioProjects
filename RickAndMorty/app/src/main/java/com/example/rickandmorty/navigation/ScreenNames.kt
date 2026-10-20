package com.example.rickandmorty.navigation

sealed class ScreenNames(val route: String) {
    data object Lista : ScreenNames("lista")

    data object Detalle : ScreenNames("detalle/{id}") {
        fun createRoute(id: Int) = "detalle/$id"
    }
}
