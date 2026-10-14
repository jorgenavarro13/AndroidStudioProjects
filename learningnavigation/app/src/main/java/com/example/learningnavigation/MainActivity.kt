package com.example.learningnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.learningnavigation.ui.theme.LearningnavigationTheme

sealed class Screen(val route:String){
    data object A: Screen("screenA")
    data object B: Screen("screenB")
    data object C: Screen("screenC")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LearningnavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SimpleNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleNavigation( modifier: Modifier = Modifier) {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = Screen.A.route
    ){
        // Registrar pantallas
        composable(Screen.A.route){
            ScreenA(
                goToB = { nav.navigate(Screen.B.route)},
                goToC = { nav.navigate(Screen.C.route)}
            )

        }

        composable(Screen.B.route){
            ScreenB(
                onBack = { nav.popBackStack()},
                goToC = { nav.navigate(Screen.C.route)}
            )
        }

        composable(Screen.C.route){
            ScreenC(
                onBack = { nav.popBackStack()}
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NavWithParams(modifier: Modifier = Modifier) {
    /* TODO  */
}

@Preview(showBackground = true)
// 3) A: captura Int y navega a B con userId
@Composable
fun ScreenA(goToB: (Int) -> Unit = {}, goToC: () -> Unit = {}) {
    var input by remember { mutableStateOf("42") } // valor inicial para demo

    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pantalla A", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = input,
            onValueChange = { input = it },
            label = { Text("userId (Int)") }
        )

        Spacer(Modifier.height(12.dp))
        Button(onClick = { input.toIntOrNull()?.let(goToB) }) {
            Text("Ir a B con userId")
        }

        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = goToC) { Text("Ir a C") }
    }
}
@Preview(showBackground = true)
// 4) B: muestra userId y opciones de back/clear
@Composable
fun ScreenB(userId: Int = 42, onBack: () -> Unit = {}, goToC: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pantalla B", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("userId = $userId")

        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Atrás") }

        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = goToC) {
            Text("Ir a C")
        }
    }
}

@Preview(showBackground = true)
// 5) C: simple con botón de regreso
@Composable
fun ScreenC(onBack: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Pantalla C", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Button(onClick = onBack) { Text("Atrás") }
    }
}