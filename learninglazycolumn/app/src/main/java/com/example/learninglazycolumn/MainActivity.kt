package com.example.learninglazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learninglazycolumn.ui.theme.LearninglazycolumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearninglazycolumnTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val lista_nombres = listOf("Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana", "Ana", "Luis","Carlos", "Diana")

    LazyColumn(modifier = modifier){
        items(lista_nombres){ nombre ->
            NombreItem(nombre)
            // Text(it) Si no quieres usar el nombre-> debes usar el iterador
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NombreItem(nombre: String = "Luis"){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        shape = RoundedCornerShape (12.dp),
        color = Color (0XFFBBDEFB)
    ){
        Box(modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center){

            Text( text = nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color (0XFF0D47A1)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearninglazycolumnTheme {
        Greeting("Android")
    }
}