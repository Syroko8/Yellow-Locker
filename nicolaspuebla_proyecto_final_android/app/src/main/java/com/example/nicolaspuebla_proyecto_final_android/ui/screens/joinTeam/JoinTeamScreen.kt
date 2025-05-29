package com.example.nicolaspuebla_proyecto_final_android.ui.screens.joinTeam

import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.QrCode2
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.nicolaspuebla_proyecto_final_android.R
import com.example.nicolaspuebla_proyecto_final_android.data.model.dataClases.Team
import com.example.nicolaspuebla_proyecto_final_android.ui.components.ErrorDialog
import com.example.nicolaspuebla_proyecto_final_android.utils.SessionManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun JoinTeamScreen(viewModel: JoinTeamScreenViewModel = hiltViewModel()){

    val errMsg by viewModel.errorMessage.collectAsState()
    val choseTeam by viewModel.choseTeam.collectAsState()
    val chosenTeam by viewModel.chosenTeam.collectAsState()
    val joined by viewModel.successfullyJoined.collectAsState()
    val logout by viewModel.logout.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.getTeams()
    }

    LaunchedEffect(viewModel.teamName) {
        viewModel.filterTeams()
    }

    LaunchedEffect(joined) {
        if(joined){
            showAchievedToast(scope, context = context, viewModel)
        }
    }

    LaunchedEffect(logout) {
        if(logout){
            SessionManager.setLogOut(true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background((Color(244, 235, 235)))
            .padding(horizontal = 40.dp)
    ) {
        Spacer(Modifier.height(40.dp))
        SearchBar(viewModel)
        List(viewModel)

        if(errMsg != ""){
            ErrorDialog(
                errMsg,
                onOk = { viewModel.unsetError() },
                onRetry = { viewModel.getTeams() }
            )
        }

        if(choseTeam && (chosenTeam != null)){
            ChoiceDialog(
                viewModel,
                onConfirm = {
                    viewModel.setChoseTeam(false)
                    if(viewModel.checkAlreadyBelong()){
                        showAlreadyBelongToast(scope, context = context, viewModel)
                    } else {
                        viewModel.joinTeam(it.id)
                    }
                },
                onDismiss = {
                    viewModel.setChoseTeam(false)
                    viewModel.unsetChosenTeam()
                }
            )
        }
    }
}

@Composable
fun SearchBar(viewModel: JoinTeamScreenViewModel){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(Color(217, 217, 217))
            .clip(RoundedCornerShape(10.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        TextField(
            value = viewModel.teamName.value,
            onValueChange = {
                viewModel.teamName.value = it
                viewModel.filterTeams()
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.team_name),
                    fontFamily = FontFamily(Font(R.font.jura_bold)),
                    color = Color.Black

                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(R.string.search_icon_description),
                    tint = Color.Black
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.weight(4f)
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color(89, 159, 229))
        ) {
            Button(
                onClick = {  },
                modifier = Modifier.fillMaxSize(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(10),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    imageVector =  Icons.Rounded.QrCode2,
                    contentDescription = stringResource(R.string.qr_icon_description),
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    }
}

@Composable
fun List(viewModel: JoinTeamScreenViewModel) {
    val loading by viewModel.loading.collectAsState()
    val list by viewModel.filteredTeamList.collectAsState()
    val teamName = viewModel.teamName.value

    if (loading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    } else if(teamName == "") {
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 40.dp, end = 40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.search_team_demand),
                fontFamily = FontFamily(Font(R.font.jura_regular)),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    } else if((teamName != "") && (list.isEmpty())){
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 40.dp, end = 40.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(R.string.search_team_empty),
                fontFamily = FontFamily(Font(R.font.jura_regular)),
                fontSize = 18.sp,
                color = Color.Black
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 15.dp, bottom = 8.dp)
        ) {
            items(list){
                TeamCard(
                    it,
                    onClick = { team ->
                        viewModel.setChoseTeam(true)
                        viewModel.setChosenTeam(team)
                    }
                )
            }
        }
    }
}

@Composable
fun TeamCard(team: Team, onClick: (Team) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = { onClick(team) }
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
                    text = team.locality.name,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Text(
                    text = team.sport,
                    fontFamily = FontFamily(Font(R.font.jura_semi_bold)),
                    color = Color.Black,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun ChoiceDialog(
    viewModel: JoinTeamScreenViewModel,
    onDismiss: () -> Unit,
    onConfirm: (Team) -> Unit
) {

    val chosenTeam by viewModel.chosenTeam.collectAsState()

    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Red.copy(alpha = 0.8F)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "Exit app",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Text(
                    text = "${stringResource(R.string.join_group_choice_question)} ${chosenTeam?.name}?",
                    modifier = Modifier.padding(8.dp)
                )

                Row(Modifier.padding(top = 10.dp)) {
                    OutlinedButton(
                        onClick = { onDismiss() },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = stringResource(R.string.cancel))
                    }

                    Button(
                        onClick = { onConfirm(chosenTeam!!) },
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .weight(1F)
                    ) {
                        Text(text = stringResource(R.string.confirm))
                    }
                }
            }
        }
    }
}

fun showAchievedToast(scope: CoroutineScope, context: Context, viewModel: JoinTeamScreenViewModel){
    val toast = Toast(context).apply {
        duration = Toast.LENGTH_LONG
        view = LayoutInflater.from(context).inflate(R.layout.achieved_join_team_toast_layout, null)
    }
    toast.show()
    scope.launch {
        delay(toast.duration.toLong())
        viewModel.unsetSuccessfullyJoined()
        viewModel.unsetChosenTeam()
    }
}

fun showAlreadyBelongToast(scope: CoroutineScope, context: Context, viewModel: JoinTeamScreenViewModel){
    val toast = Toast(context).apply {
        duration = Toast.LENGTH_LONG
        view = LayoutInflater.from(context).inflate(R.layout.already_belong_team_toast_layout, null)
    }
    toast.show()
    scope.launch {
        delay(toast.duration.toLong())
        viewModel.unsetChosenTeam()
    }
}