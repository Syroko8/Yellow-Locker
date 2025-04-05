package com.example.nicolaspuebla_proyecto_final_android.ui.screens.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import androidx.compose.foundation.lazy.items


@Composable
fun LandingScreen(onNav: (String, Int?) -> Unit, viewModel: LandingScreenViewModel = hiltViewModel()){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244,235,235)))
    ){
        Title()
        TeamList(viewModel)
    }
}

@Composable
fun Title(){
    Column(
        modifier = Modifier
            .padding(top = 30.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = stringResource(R.string.landing_title),
                fontFamily = FontFamily(Font(R.font.jura_bold)),
                fontSize = 36.sp,
                color = Color.Black
            )
            Spacer(Modifier.height(10.dp))
            HorizontalDivider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(start = 40.dp, end = 40.dp))
    }
}

@Composable
fun TeamList(viewModel: LandingScreenViewModel){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 40.dp, end = 40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(viewModel.teamList.value.size != 0){
            items(viewModel.teamList.value){ team ->
                TeamCard(team, viewModel.getTeamLocality(team.locality))
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

@Composable
fun TeamCard(team: Team, locality: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
            ){
                AsyncImage(
                    model = team.logo,
                    contentDescription = stringResource(R.string.team_logo_description),
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp))
                )
            }

            Spacer(Modifier.width(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = team.name,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )

                Text(
                    text = locality,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}
