package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.laboratorio_b.ui.navigation.Destinations
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager

@Composable
fun TeamWelcomeScreen(
    onNav: (String) -> Unit,
    teamId: Int,
    viewModel: TeamWelcomeScreenViewModel = hiltViewModel()
){
    val team = viewModel.team.collectAsState().value
    val actualTeamId by SessionManager.actualTeamId.collectAsState()
    val userId = SessionManager.user?.id

    LaunchedEffect(Unit) {
        viewModel.getTeam(teamId.toLong())
    }

    LaunchedEffect(SessionManager.leaveActualTeam.value) {
        if(SessionManager.leaveActualTeam.value){
            actualTeamId?.let {
                if (userId != null) {
                    viewModel.leaveTeam(userId, it)
                }
            }
        }
    }

    LaunchedEffect(viewModel.leftTeam.value) {
        if(viewModel.leftTeam.value){
            onNav(Destinations.LANDING_SCREEN)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244, 235, 235)))
    ) {
        Title(team)
        Logo(team)
        TeamInfo(team, viewModel)
    }
}

@Composable
fun Title(team: Team?){
    Row(
        modifier = Modifier
            .padding(top = 40.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = team?.name ?: stringResource(R.string.team_name),
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
            .padding(vertical = 40.dp)
            .fillMaxWidth()
            .height(320.dp),
        horizontalArrangement = Arrangement.Center
    ){
        AsyncImage(
            model = R.drawable.team_shield,
            contentDescription = stringResource(R.string.welcome_team_logo),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun TeamInfo(team: Team?, viewModel: TeamWelcomeScreenViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 40.dp)
            .wrapContentHeight()
    ) {
        InfoText(
            label = stringResource(R.string.welcome_locality_label),
            value = team?.locality?.name ?: stringResource(R.string.not_found)
        )

        InfoText(
            label = stringResource(R.string.welcome_members_label),
            value = team?.members?.size.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_matches_label),
            value = viewModel.matches.value.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_victories_label),
            value = viewModel.victories.value.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_loses_label),
            value = viewModel.loses.value.toString()
        )

        InfoText(
            label = stringResource(R.string.welcome_draws_label),
            value = viewModel.draws.value.toString()
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
        Spacer(Modifier.width(10.dp))
        Text(
            text = value,
            color = Color.Black,
            fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
            fontSize = 18.sp
        )
    }
}