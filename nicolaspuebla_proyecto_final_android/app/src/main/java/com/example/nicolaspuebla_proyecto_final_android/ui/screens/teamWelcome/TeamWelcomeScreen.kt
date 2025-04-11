package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team

@Composable
fun TeamWelcomeScreen(
    onNav: (String, Int?) -> Unit,
    teamId: Int,
    viewModel: TeamWelcomeScreenViewModel = hiltViewModel()
){

    val team = viewModel.team.collectAsState().value
    val locality = viewModel.locality.collectAsState().value

    LaunchedEffect(teamId) {
        viewModel.getTeam()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244,235,235)))
    ) {
        Title(team)
        Logo(team)
        TeamInfo(team, locality)
    }
}

@Composable
fun Title(team: Team?){
    Row(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = team?.name ?: "",
            fontSize = 35.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            color = Color.Black,
            softWrap = true,
            lineHeight = 50.sp
        )
    }
}

@Composable
fun Logo(team: Team?){
    Row(
        modifier = Modifier
            .padding(top = 80.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ){

        AsyncImage(
            model = team?.logo,
            contentDescription = stringResource(R.string.welcome_team_logo),
            modifier = Modifier
                .size(300.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun TeamInfo(team: Team?, locality: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp)
            .wrapContentHeight()
    ) {
        InfoText(
            label = stringResource(R.string.welcome_locality_label),
            value = locality
        )

        InfoText(
            label = stringResource(R.string.welcome_members_label),
            value = team?.members?.size.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_matches_label),
            value = team?.members?.size.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_victories_label),
            value = team?.members?.size.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_loses_label),
            value = team?.members?.size.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_draws_label),
            value = team?.members?.size.toString()
        )
    }
}

@Composable
fun InfoText(label: String, value: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "${label}:",
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            fontSize = 18.sp
        )

        Text(
            text = value,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            fontSize = 18.sp
        )
    }
}