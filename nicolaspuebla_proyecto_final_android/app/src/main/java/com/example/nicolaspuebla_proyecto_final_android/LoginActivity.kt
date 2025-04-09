package com.example.nicolaspuebla_proyecto_final_android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio_b.ui.navigation.InitialAppNavGraph
import com.example.nicolaspuebla_proyecto_final_android.ui.theme.Nicolaspuebla_proyecto_final_androidTheme
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Nicolaspuebla_proyecto_final_androidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    App(advance = {continueToLanding()})
                }
            }
        }
    }

    fun continueToLanding(){
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(advance: () -> Unit){

    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val currentRoute = remember { mutableStateOf<String?>(null) }

    /**
     * Efecto para actualizar la ruta actual siempre que cambie el destino de navegación.
     */
    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route
        }
    }

    LaunchedEffect(SessionManager.logged) {
        if(SessionManager.logged){
            advance()
        }
    }

    Scaffold { innerPadding ->
        InitialAppNavGraph(
            navController = navController,
            innerPadding = innerPadding,
            onNav = { route ->
                scope.launch {
                    if (currentRoute.value != route) {
                        navController.navigate(route)
                    }
                }
            },
            onLogin = { advance() }
        )
    }
}
