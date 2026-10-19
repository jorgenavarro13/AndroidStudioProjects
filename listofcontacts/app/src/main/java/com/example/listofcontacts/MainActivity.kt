package com.example.listofcontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.dropUnlessResumed
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listofcontacts.model.getContacts
import com.example.listofcontacts.navigation.ScreenNames
import com.example.listofcontacts.screens.ContactDetail
import com.example.listofcontacts.screens.ContactsScreen
import com.example.listofcontacts.ui.theme.ListofcontactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListofcontactsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactsApp(modifier = Modifier.padding(innerPadding))
                    //  This is our navigation also
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactsApp( modifier : Modifier = Modifier){
    val nav = rememberNavController()
    val contacts = remember { getContacts() }

    NavHost(
        navController = nav,
        startDestination = ScreenNames.Contacts.route,
        modifier = modifier
    ){
        composable (ScreenNames.Contacts.route){
            ContactsScreen(
                contacts = contacts,
                onItemClick = {p-> nav.navigate(ScreenNames.Details.createRoute(p.id))}
            )
        }

        composable(
            route = ScreenNames.Details.route,
            arguments = listOf(navArgument("id") {type= NavType.IntType})
        ){ backStackEntry ->
            val id= backStackEntry.arguments?.getInt("id")
            val contact = contacts.firstOrNull(){it.id == id}
            ContactDetail(contact=contact, modifier = modifier, onBack = dropUnlessResumed{nav.popBackStack()})
        }
    }
}