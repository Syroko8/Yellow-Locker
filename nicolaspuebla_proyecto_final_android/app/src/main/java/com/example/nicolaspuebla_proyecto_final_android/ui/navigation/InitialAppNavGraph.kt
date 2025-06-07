package com.example.laboratorio_b.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.login.LoginScreen
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup2.SignUp2Screen
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup.SignUpScreen

/**
 * Gráfico de navegación inicial de la aplicación, manejando las transiciones de las pantallas de inicio de sesión y registro.
 *
 * @param navController La instancia de NavHostController para la navegación.
 * @param innerPadding Espacio que se aplica alrededor del área de contenido.
 * @param onNav Callback invocado para navegar a una ruta específica.
 * @param onLogin Callback que se ejecuta si el usuario ha iniciado sesión con éxito.
 */
@Composable
fun InitialAppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNav: (String) ->Unit,
    onLogin: () -> Unit
){

    Column(Modifier.padding(innerPadding)){
        NavHost(navController = navController, startDestination = Destinations.LOGIN) {

            composable (Destinations.LOGIN){
                LoginScreen(
                    onNav = { onNav(it) },
                    onLogin = { onLogin() }
                )
            }

            composable (Destinations.SIGN_UP){
                SignUpScreen(
                    onNav = { onNav(it) },
                )
            }

            composable (Destinations.SIGN_UP_2){
                SignUp2Screen(
                    onNav = { onNav(it) },
                )
            }
        }
    }
}