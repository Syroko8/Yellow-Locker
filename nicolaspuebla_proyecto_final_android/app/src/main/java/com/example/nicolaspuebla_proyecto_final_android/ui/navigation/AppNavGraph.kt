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
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.signup.LoginButtonRow2

@Composable
fun AppNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onNav: (String) ->Unit
){

    var value: Int = 0

    fun changeValue(newValue: Int?){
        if(newValue != null){
            value = newValue
        }
    }

    fun processNavigation(nextScreen: String, value: Int?){
        changeValue(value)
        onNav(nextScreen)
    }

    Column(Modifier.padding(innerPadding)){
        NavHost(navController = navController, startDestination = Destinations.LOGIN) {

            composable (Destinations.LOGIN){
                LoginScreen(
                    innerPadding = innerPadding,
                    onNav = { nextScreen:String, value: Int? -> processNavigation(nextScreen, value) }
                )
            }


        }
    }
}