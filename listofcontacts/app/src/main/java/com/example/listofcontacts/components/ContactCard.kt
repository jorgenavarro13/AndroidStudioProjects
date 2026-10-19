package com.example.listofcontacts.components

import android.R.attr.description
import android.graphics.Paint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listofcontacts.model.Contact
import com.example.listofcontacts.model.calculateAbreviationContactName
import com.example.listofcontacts.ui.theme.LocalContactSurfaceColors
import com.example.listofcontacts.ui.theme.contactAvatarColorsLight
import java.nio.file.Files.size
import kotlin.Char
import kotlin.math.min

@Preview(showBackground = true)
@Composable
fun ContactCard(
    contact: Contact =    Contact(1, "Jorge Arturo","Montiel Navarro", "4961552910", "juanvalentinarenas@gmail.com", "Cerro de la Conformidad 2709", "John Deere"),
    whatToDo: (Contact) -> Unit = {}
){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

        shape = RoundedCornerShape(25.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = LocalContactSurfaceColors.current.cardBackground,
        )
    ){
        val avatarPalette =  contactAvatarColorsLight
        val colorData = avatarPalette[contact.id % avatarPalette.size]
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.CenterVertically

        ){
            Surface(
                modifier = Modifier,
                shape = CircleShape,
                color = colorData.background) {
                Text(calculateAbreviationContactName(contact.name.trim(), contact.lastname.trim()), style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.SemiBold, color=colorData.content , modifier=Modifier.padding(9.dp))
            }

            Column(Modifier.padding(start = 12.dp).weight(1f)){
                Text(contact.name+ " " + contact.lastname, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, textAlign = TextAlign.Left)
                Text(contact.phone, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Light)
            }
            Column(Modifier.padding(8.dp),
            ) {
                IconButton(onClick = { whatToDo(contact) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                        contentDescription = "Detalle",
                        modifier = Modifier.defaultMinSize(10.dp)
                    )
                }


            }
        }
    }
}

