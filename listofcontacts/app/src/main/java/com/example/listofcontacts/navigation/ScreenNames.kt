package com.example.listofcontacts.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class ScreenNames (val route: String){
    data object Contacts : ScreenNames("contacts")

    data object Details : ScreenNames("details/{id}"){
        fun createRoute(id: Int) = "details/$id"
    }

}