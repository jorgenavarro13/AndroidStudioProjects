package com.example.listofcontacts.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listofcontacts.components.ContactCard
import com.example.listofcontacts.model.Contact
import com.example.listofcontacts.model.getContacts
import com.example.listofcontacts.ui.theme.LocalContactSurfaceColors

@Composable
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
fun ContactsScreen(
    contacts: List<Contact> = getContacts(),
    onItemClick: (Contact) -> Unit = {} ,
    modifier: Modifier = Modifier  )
{
    Scaffold(
        topBar= {
            TopAppBar(
                title = { Text("Contactos", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Left, fontWeight = FontWeight.SemiBold, modifier= Modifier.padding(10.dp))}
            )
        }
    ){ innerPadding->
        LazyColumn(modifier = modifier.padding(innerPadding).background(color= LocalContactSurfaceColors.current.listBackground)) {
            items (contacts) { contact->
                ContactCard(contact=contact, whatToDo = onItemClick)
            }
        }
    }
}
