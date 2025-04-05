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

@Composable
fun InitialAppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNav: (String) ->Unit
){

    Column(Modifier.padding(innerPadding)){
        NavHost(navController = navController, startDestination = Destinations.LOGIN) {

            composable (Destinations.LOGIN){
                LoginScreen(
                    onNav = { onNav(it) }
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