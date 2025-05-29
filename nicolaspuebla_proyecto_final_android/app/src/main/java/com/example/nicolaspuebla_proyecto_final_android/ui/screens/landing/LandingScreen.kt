package com.example.nicolaspuebla_proyecto_final_android.ui.screens.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.ui.components.TeamCard

@Composable
fun LandingScreen(onNav: (String, Int?) -> Unit, viewModel: LandingScreenViewModel = hiltViewModel()){

    val loading by viewModel.loading.collectAsState()
    val errMsg by viewModel.errorMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUser()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244, 235, 235)))
    ){
        Title()
        if(loading){
           Column(
               modifier = Modifier
                   .fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               CircularProgressIndicator()
           }
        } else {
            TeamList(viewModel, onNav = { screen, id -> onNav(screen, id) })
        }

        if(errMsg != ""){
            ErrorDialog(
                errMsg,
                onRetry = { viewModel.getUser() },
                onOk = { viewModel.unsetError() }
            )
        }
    }
}

@Composable
fun Title(){
    Column(
        modifier = Modifier
            .padding(top = 30.dp, start = 40.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
            Text(
                text = "${stringResource(R.string.landing_title)}:",
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                fontSize = 28.sp,
                color = Color.Black
            )
    }
}

@Composable
fun TeamList(viewModel: LandingScreenViewModel, onNav: (String, Int?) -> Unit){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(viewModel.teamList.value.isNotEmpty()){
            items(viewModel.teamList.value){ team ->
                TeamCard(
                    team,
                    onNav = { screen, id -> onNav(screen, id)},
                    destination = Destinations.TEAM_WELCOME
                )
            }
        } else{
            item {
                NoTeams()
            }
        }
    }
}

@Composable
fun NoTeams(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_landing_teams),
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
    }
}

