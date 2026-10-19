package com.example.listofcontacts.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listofcontacts.model.Contact
import androidx.compose.ui.platform.LocalContext
import com.example.listofcontacts.model.calculateAbreviationContactName
import com.example.listofcontacts.ui.theme.LocalContactSurfaceColors
import com.example.listofcontacts.ui.theme.callButtonBackground
import com.example.listofcontacts.ui.theme.contactAvatarColorsLight
import com.example.listofcontacts.ui.theme.onPrimaryContainerLight

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ContactDetail (
    contact : Contact? = Contact(1, "Juan Valentin","Arenas", "4961552910", "juanvalentinarenas@gmail.com", "Cerro de la Conformidad 2709", "John Deere"),
    modifier: Modifier = Modifier,
    onBack : () -> Unit = {}
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Left, fontWeight = FontWeight.SemiBold, modifier= Modifier.padding(10.dp))},
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        }
    ) {innerPadding->
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        if(contact == null){
            Text("Contact not found")
        }else{
            val avatarPalette =  contactAvatarColorsLight
            val context = LocalContext.current
            val colorData = avatarPalette[contact.id % avatarPalette.size]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Surface(
                    modifier = Modifier,
                    shape = CircleShape,
                    color = colorData.background
                ){
                    Text(text=calculateAbreviationContactName(contact.name.trim(), contact.lastname.trim()),
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold,
                        color = colorData.content,
                        modifier=Modifier.padding(5.dp))
                }
                Spacer(Modifier.height(8.dp))
                Text(contact.name.trim()+" "+contact.lastname.trim(), style = MaterialTheme.typography.displaySmall, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold)
                Text("Contacto #${contact.id}")
                Spacer(Modifier.height(20.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth().padding(horizontal=15.dp),
                    shape= RoundedCornerShape(25.dp),
                    color = LocalContactSurfaceColors.current.detailSurfaceBackground
                ) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                        ){
                        Row(modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(Icons.Rounded.Phone,
                                contentDescription = "enterprise icon",
                                modifier = Modifier.padding(8.dp)
                            )
                            Column(modifier = Modifier.padding(3.dp)){
                                Text("Teléfono", fontWeight = FontWeight.SemiBold)
                                Text(contact.phone, style= MaterialTheme.typography.bodyLarge)
                            }
                        }

                        Row(modifier = Modifier,
                            verticalAlignment = Alignment.CenterVertically){
                            Icon(Icons.Rounded.Email,
                                contentDescription = "enterprise icon",
                                modifier = Modifier.padding(8.dp)
                            )
                            Column(modifier = Modifier.padding(1.dp)){
                                Text("Correo", fontWeight = FontWeight.SemiBold)
                                Text(contact.email, style= MaterialTheme.typography.bodyLarge)
                            }
                        }

                        if(contact.address != null){
                            Row(modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically){
                                Icon(Icons.Rounded.Home,
                                    contentDescription = "enterprise icon",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Column(){
                                    Text("Dirección", fontWeight = FontWeight.SemiBold)
                                    Text(contact.address, style= MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }

                        if(contact.enterprise != null){
                            Row(modifier = Modifier,
                                verticalAlignment = Alignment.CenterVertically){
                                Icon(Icons.Rounded.Work,
                                    contentDescription = "enterprise icon",
                                    modifier = Modifier.padding(8.dp)
                                    )
                                Column(){
                                    Text("Empresa", fontWeight = FontWeight.SemiBold)
                                    Text(contact.enterprise, style= MaterialTheme.typography.bodyLarge)
                                }
                            }
                        }
                    }
                }
                Spacer(Modifier.height(8.dp))
                Row(){


                    Button(
                        onClick = {},
                        elevation = ButtonDefaults.elevatedButtonElevation(10.dp, pressedElevation = 100.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = callButtonBackground, // Color de fondo del botón
                            contentColor = Color.White          // Color del texto
                        )
                    ) { Text("Llamar") }
                    Spacer(Modifier.width((10.dp)))
                    Button(
                        onClick = {sendEmail(context=context,contact.email.trim(), "Correo Android", "Holaa, correo desde Android")},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = onPrimaryContainerLight, // Color de fondo del botón
                            contentColor = Color.White          // Color del texto
                        )
                    ) {Text("Enviar correo")}
                }

            }
        }

    }
    }
}

fun sendEmail(context: Context, email: String, subject: String, body: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, body)
    }

    try {
        context.startActivity(Intent.createChooser(intent, "Selecciona una aplicación de correo"))
    } catch (e: Exception) {
        Toast.makeText(context, "No tienes ninguna aplicación de correo instalada", Toast.LENGTH_SHORT).show()
    }
}