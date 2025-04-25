package com.example.nicolaspuebla_proyecto_final_android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.laboratorio_b.ui.navigation.AppNavGraph
import com.example.nicolaspuebla_proyecto_final_android.data.preferences.PreferencesViewModel
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabMenu
import com.example.nicolaspuebla_proyecto_final_android.ui.components.FabState
import com.example.nicolaspuebla_proyecto_final_android.ui.components.TopBar
import com.example.nicolaspuebla_proyecto_final_android.ui.theme.Nicolaspuebla_proyecto_final_androidTheme
import com.example.nicolaspuebla_proyecto_final_android.utils.ButtonItemLists
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fun logout() {
            SessionManager.setLogOut(false)
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
            finish()
        }

        setContent {
            Nicolaspuebla_proyecto_final_androidTheme {
                App(onLogOutIntent = { logout() })
            }
        }
    }
}

@Composable
fun App(onLogOutIntent: () ->Unit, viewModel: PreferencesViewModel = hiltViewModel()){

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

    /**
     * Si la variable logOut del session manager cambia su valor, se ejecutará el método onLogOutIntent.
     */
    LaunchedEffect(SessionManager.logOut){
        SessionManager.logOut.collect{ logOutVal ->
            if (logOutVal) {
                viewModel.clearUserData()
                onLogOutIntent()
            }
        }
    }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBar()
        },
        floatingActionButton = {

            var landingScreenMenuState by remember{
                mutableStateOf(FabState.Colapsed)
            }
            val rol by SessionManager.actualTeamRole.collectAsState()
            var items = ButtonItemLists.getButtonListForView(currentRoute.value?:"", rol ?: TeamRoles.Player)

            FabMenu(
                state = landingScreenMenuState,
                onFloatingStateChange = {
                    landingScreenMenuState = it
                },
                items = items,
                onItemClick = { route ->
                    scope.launch {
                        if (currentRoute.value != route) {
                            navController.navigate(route)
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        /**
         * Gráfico de navegación de la aplicación, manejando las transiciones de pantalla.
         *
         * @param navController La instancia de NavController para la navegación.
         * @param innerPadding Relleno que se aplica alrededor del área de contenido.
         * @param onNav Callback invocado para navegar a una ruta específica.
         */
        AppNavGraph(
            navController = navController,
            innerPadding = innerPadding,
            onNav = { route ->
                scope.launch {
                    if (currentRoute.value != route) {
                        navController.navigate(route)
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun MainPreview(){
    MainActivity()
}