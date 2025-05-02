package com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMatches

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import java.time.format.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Match
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.ui.components.MatchCard
import com.example.nicolaspuebla_proyecto_final_android.ui.screens.teamMembers.Title
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import com.example.nicolaspuebla_proyecto_final_android.utils.TeamRoles
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TeamMatchesScreen(viewModel: TeamMatchesScreenViewModel = hiltViewModel()) {

    val teamId by SessionManager.actualTeamId.collectAsState()
    val err by viewModel.errorMessage.collectAsState()
    val loading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        teamId?.let { viewModel.getMatches(it) }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background((Color(244,235,235)))
            .padding(top = 10.dp )
    ) {
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
            List(viewModel)
        }

        if(err != ""){
            ErrorDialog(
                err =  err,
                onRetry = {  teamId?.let { viewModel.getMatches(it) } },
                onOk = { viewModel.unsetError() }
            )
        }
    }
}

@Composable
fun Title(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 17.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = stringResource(R.string.members),
            fontFamily = FontFamily(Font(R.font.jura_bold)),
            fontSize = 28.sp,
            color = Color.Black
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        HorizontalDivider(color = Color.Black, thickness = 2.dp, modifier = Modifier.padding(start = 15.dp, end = 15.dp))
    }
}

@Composable
fun List(viewModel: TeamMatchesScreenViewModel){

    val matches by viewModel.matches.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, start = 40.dp, end = 40.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(matches.isNotEmpty()){
            var lastMatch: Match? = null

            items(items = matches.sortedBy { it.date }){

                if((lastMatch == null) || (it.date != lastMatch!!.date)){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 10.dp, bottom = 15.dp)
                    ) {
                        Text(
                            text = getFormattedDate(it.date),
                            fontFamily = FontFamily(Font(R.font.jura_bold)),
                            fontSize = 18.sp,
                            color = Color(61, 147, 247)
                        )
                    }
                }
                MatchCard(
                    match = it,
                    onClick = {
                        if(SessionManager.actualTeamRole.value == TeamRoles.Captain){

                        }
                    }
                )
                lastMatch = it
            }
        } else {
            item {
                NoMatches()
            }
        }
    }
}

@Composable
fun NoMatches(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.no_matches),
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.jura_bold))
        )
    }
}

@Composable
fun getFormattedDate(dateString: String): String {
    return try {
        val localDate = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE)
        val dayOfWeek = localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH)
        val monthName = localDate.month.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

        "${localDate.dayOfMonth} $monthName - $dayOfWeek"
    } catch (e: Exception) {
        stringResource(R.string.invalid_date)
    }
}