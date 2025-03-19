package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamWelcome

import android.media.tv.TvContract.Channels.Logo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.Team

@Composable
fun TeamWelcomeScreen(innerPadding: PaddingValues, viewModel: TeamWelcomeScreenViewModel = hiltViewModel()){

    val team = viewModel.team.collectAsState().value

    Column(
        modifier = Modifier
            .padding(innerPadding)
    ) {
        Title(team)
        Logo(team)
    }
}

@Composable
fun Title(team: Team?){
    Row(
        modifier = Modifier
            .padding(top = 50.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ){
        Text(
            text = team?.name ?: "",
            fontSize = 40.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
    }
}

@Composable
fun Logo(team: Team?){
    Row(
        modifier = Modifier
            .padding(top = 50.dp)
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